import java.text.SimpleDateFormat;
import java.util.List;

import org.crsh.cli.Argument;
import org.crsh.cli.Command;
import org.crsh.cli.Usage;
import org.crsh.command.BaseCommand;
import org.crsh.command.InvocationContext;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Component;

import com.julien_roux.jug.quickies.model.Quicky;
import com.julien_roux.jug.quickies.repository.QuickyRepository;

@Usage("Quicky app")
@Component
public class quickies extends BaseCommand {

	@Command
	@Usage("List all quickies")
	public String list(InvocationContext<Object> context, @Usage("specify the usergroup") @Argument String usergroup) {
		StringBuilder response = new StringBuilder();
		QuickyRepository repo = (QuickyRepository) getRepository(context, QuickyRepository.class);

		List<Quicky> quickies = usergroup == null ? repo.findAll() : repo.findByUsergroup(usergroup);
		for (Quicky quicky : quickies) {
			response.append(String.format("Title: %s  | Date: %s  |  Location: %s  |  Usergroup: %s\n",
			            quicky.getTitle(), new SimpleDateFormat("yyyy-DD-mm HH:mm").format(quicky.getSubmissionDate()),
			            quicky.getLocation(), quicky.getUsergroup()));
		}

		return response.toString();
	}

	private Object getRepository(InvocationContext<Object> context, Class clazz) {
		BeanFactory beanFactory = (BeanFactory) context.getAttributes().get("spring.beanfactory");
		return beanFactory.getBean(clazz);
	}
}