import org.kohsuke.github.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Work1 {
	public static void main(String[] args) throws IOException {
		String repoName = "whiteship/live-study";

		GitHub gitHub = GitHubBuilder.fromEnvironment().build();
		GHRepository repository = gitHub.getRepository(repoName);
		List<GHIssue> listIssues = getIssues(repository);

		for(GHIssue item : listIssues){
			List<GHIssueComment> comments = item.getComments();
			for(GHIssueComment item2 : comments){
				System.out.println("너가 커멘트 생성자냐 : "+item2.getUser().getLogin());
			}
		}
	}

	private static List<GHIssue> getIssues(GHRepository gitRepo) throws IOException {
		 return gitRepo.listIssues(GHIssueState.ALL).toList();
	}
}
