package backend.challenge.modules.task.infra.http.views;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskProgressView {

	private int progress;

	public boolean validateIfProgressIsValid() {
		return this.progress >= 0 && this.progress <= 100;
	}

}
