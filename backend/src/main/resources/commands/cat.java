import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.crsh.cli.Command;
import org.crsh.cli.Usage;
import org.crsh.command.BaseCommand;
import org.crsh.command.ScriptException;
import org.fabianm.brainfuck.BrainfuckEngine;
import org.springframework.stereotype.Component;

@Usage("Brainfuck cat command")
@Component
public class cat extends BaseCommand {
	@Command
	@Usage("Print brainfuck ascii cats")
	public String miaou() {
		String response = "Miaouuuu";
		
		try {
			BufferedReader buff = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/bf/cat.bf")));
			String script = buff.readLine();
			
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			
			BrainfuckEngine engine = new BrainfuckEngine(script.length(), os);
			engine.interpret(script);

			response = os.toString();
		} catch (Exception e) {
			throw new ScriptException("Pas Miaou !");
		}
		
		return response;
	}
}
