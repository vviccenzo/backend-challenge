package backend.challenge.modules.task.infra.http.views;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskProgressView {

	private int progress;

	public boolean validateIfProgressIsValid() {
		return this.progress >= 0 && this.progress <= 100;
	}

}
