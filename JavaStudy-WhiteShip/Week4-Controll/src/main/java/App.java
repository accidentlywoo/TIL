import org.kohsuke.github.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
	public static void main(String[] args) throws IOException {
		String repoName = "whiteship/live-study";
		GHRepository repository = null;
		GitHub gitHub;
		gitHub = new GitHubBuilder().withOAuthToken("2c374643be04c0f9891fab7213002c9e71ce3857").build();
		repository = gitHub.getRepository(repoName);
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
