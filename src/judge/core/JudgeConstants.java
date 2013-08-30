package judge.core;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class JudgeConstants
{
	public static ExecutorService threadPool = Executors.newFixedThreadPool(10);
}
