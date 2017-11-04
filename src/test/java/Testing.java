import javafx.event.EventHandler;

import java.util.ArrayList;
import java.util.function.Consumer;

public class Testing
{

	private Consumer<String> t;
	
	private void setOnRun(final Consumer<String> t)
	{
		this.t = t;
	}
	
	private void run(final String s)
	{
		t.accept(s);
	}
	
	public static void main(String[] args)
	{
		Testing t = new Testing();
		t.setOnRun(s -> {});
		t.run("Testing");
	}
	
}

