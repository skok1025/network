package thread;

public class UppercaseAlphabetRunableImpl extends UppercaseAlphabet implements Runnable{

	@Override
	public void run() {
		print();
	}

}
