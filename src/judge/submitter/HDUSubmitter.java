package judge.submitter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import judge.bean.Problem;
import judge.tool.ApplicationContainer;
import judge.tool.Tools;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;

public class HDUSubmitter extends Submitter
{

	static final String OJ_NAME = "HDU";
	static private DefaultHttpClient clientList[];
	static private boolean using[];
	static private String[] usernameList;
	static private String[] passwordList;

	private DefaultHttpClient client;
	private HttpGet get;
	private HttpPost post;
	private HttpResponse response;
	private HttpEntity entity;
	private HttpHost host = new HttpHost("acm.hdu.edu.cn");
	private String html;

	static
	{
		List<String> uList = new ArrayList<String>(), pList = new ArrayList<String>();
		try
		{
			FileReader fr = new FileReader(ApplicationContainer.sc.getRealPath("WEB-INF/classes/accounts.conf"));
			BufferedReader br = new BufferedReader(fr);
			while (br.ready())
			{
				String info[] = br.readLine().split("\\s+");
				if (info.length >= 3 && info[0].equalsIgnoreCase(OJ_NAME))
				{
					uList.add(info[1]);
					pList.add(info[2]);
				}
			}
			br.close();
			fr.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		usernameList = uList.toArray(new String[0]);
		passwordList = pList.toArray(new String[0]);
		using = new boolean[usernameList.length];
		clientList = new DefaultHttpClient[usernameList.length];
		HttpHost proxy = new HttpHost("127.0.0.1", 8087);
		for (int i = 0; i < clientList.length; i++)
		{
			clientList[i] = new DefaultHttpClient();
			clientList[i].getParams().setParameter(CoreProtocolPNames.USER_AGENT,
					"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.83 Safari/537.1");
			clientList[i].getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 60000);
			clientList[i].getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
			clientList[i].getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
		}

		Map<String, String> languageList = new TreeMap<String, String>();
		languageList.put("0", "G++");
		languageList.put("1", "GCC");
		languageList.put("2", "C++");
		languageList.put("3", "C");
		languageList.put("4", "Pascal");
		languageList.put("5", "Java");
		sc.setAttribute("HDU", languageList);
	}

	private void getMaxRunId() throws ClientProtocolException, IOException
	{
		Pattern p = Pattern.compile("<td height=22px>(\\d+)");

		try
		{
			get = new HttpGet("/status.php");
			response = client.execute(host, get);
			entity = response.getEntity();
			html = EntityUtils.toString(entity);
		} finally
		{
			EntityUtils.consume(entity);
		}

		Matcher m = p.matcher(html);
		if (m.find())
		{
			maxRunId = Integer.parseInt(m.group(1));
			System.out.println("maxRunId : " + maxRunId);
		} else
		{
			throw new RuntimeException();
		}
	}

	private void login(String username, String password) throws ClientProtocolException, IOException
	{
		try
		{
			post = new HttpPost("/userloginex.php?action=login&cid=0&notice=0");
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("username", username));
			nvps.add(new BasicNameValuePair("userpass", password));

			post.setEntity(new UrlEncodedFormEntity(nvps, Charset.forName("gb2312")));

			response = client.execute(host, post);
			entity = response.getEntity();

			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_MOVED_TEMPORARILY)
			{
				throw new RuntimeException();
			}
		} finally
		{
			EntityUtils.consume(entity);
		}
	}

	private boolean isLoggedIn() throws ClientProtocolException, IOException
	{
		try
		{
			get = new HttpGet("/");
			response = client.execute(host, get);
			entity = response.getEntity();
			html = EntityUtils.toString(entity);
		} finally
		{
			EntityUtils.consume(entity);
		}
		if (html.contains("href=\"/userloginex.php?action=logout\""))
		{
			return true;
		} else
		{
			return false;
		}
	}

	private void submit() throws ClientProtocolException, IOException
	{
		Problem problem = (Problem) baseService.query(Problem.class, submission.getProblem().getId());

		try
		{
			post = new HttpPost("/submit.php?action=submit");
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("check", "0"));
			nvps.add(new BasicNameValuePair("language", submission.getLanguage()));
			nvps.add(new BasicNameValuePair("problemid", problem.getOriginProb()));
			nvps.add(new BasicNameValuePair("usercode", submission.getSource()));

			post.setEntity(new UrlEncodedFormEntity(nvps, Charset.forName("gb2312")));

			response = client.execute(host, post);
			entity = response.getEntity();

			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_MOVED_TEMPORARILY)
			{
				throw new RuntimeException();
			}
		} finally
		{
			EntityUtils.consume(entity);
		}
	}

	public void getResult(String username) throws Exception
	{
		Pattern p = Pattern.compile(">(\\d{7,})</td><td>[\\s\\S]*?</td><td>([\\s\\S]*?)</td><td>[\\s\\S]*?</td><td>(\\d*?)MS</td><td>(\\d*?)K</td>");

		long cur = new Date().getTime(), interval = 2000;
		while (new Date().getTime() - cur < 600000)
		{
			try
			{
				get = new HttpGet("/status.php?user=" + username);
				response = client.execute(host, get);
				entity = response.getEntity();
				html = EntityUtils.toString(entity);
			} finally
			{
				EntityUtils.consume(entity);
			}

			Matcher m = p.matcher(html);
			if (m.find() && Integer.parseInt(m.group(1)) > maxRunId)
			{
				String result = m.group(2).replaceAll("<[\\s\\S]*?>", "").trim();
				submission.setStatus(result);
				submission.setRealRunId(m.group(1));
				if (!result.contains("ing"))
				{
					if (result.equals("Accepted"))
					{
						submission.setTime(Integer.parseInt(m.group(3)));
						submission.setMemory(Integer.parseInt(m.group(4)));
					} else if (result.contains("Compilation"))
					{
						getAdditionalInfo(submission.getRealRunId());
					}
					baseService.addOrModify(submission);
					return;
				}
				baseService.addOrModify(submission);
			}
			Thread.sleep(interval);
			interval += 500;
		}
		throw new Exception();
	}

	private void getAdditionalInfo(String runId) throws HttpException, IOException
	{
		try
		{
			get = new HttpGet("/viewerror.php?rid=" + runId);
			response = client.execute(host, get);
			entity = response.getEntity();
			html = EntityUtils.toString(entity, Charset.forName("gb2312"));
		} finally
		{
			EntityUtils.consume(entity);
		}
		submission.setAdditionalInfo(Tools.regFind(html, "(<pre>[\\s\\S]*?</pre>)"));
	}

	private int getIdleClient()
	{
		int length = usernameList.length;
		int begIdx = (int) (Math.random() * length);

		while (true)
		{
			synchronized (using)
			{
				for (int i = begIdx, j; i < begIdx + length; i++)
				{
					j = i % length;
					if (!using[j])
					{
						using[j] = true;
						client = clientList[j];
						return j;
					}
				}
			}
			try
			{
				Thread.sleep(2000);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}

	public void work()
	{
		idx = getIdleClient();
		int errorCode = 1;

		try
		{
			getMaxRunId();
			if (!isLoggedIn())
			{
				login(usernameList[idx], passwordList[idx]);
			}
			submit();
			errorCode = 2;
			submission.setStatus("Running & Judging");
			baseService.addOrModify(submission);
			getResult(usernameList[idx]);
		} catch (Exception e)
		{
			e.printStackTrace();
			submission.setStatus("Judging Error " + errorCode);
			baseService.addOrModify(submission);
		}
	}

	@Override
	public void waitForUnfreeze()
	{
		try
		{
			Thread.sleep(5000);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		} // HDU限制每两次提交之间至少隔3秒
		synchronized (using)
		{
			using[idx] = false;
		}
	}
}
