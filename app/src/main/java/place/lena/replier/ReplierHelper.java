package place.lena.replier;

import androidx.annotation.NonNull;

import com.joaomgcd.taskerpluginlibrary.config.TaskerPluginConfig;
import com.joaomgcd.taskerpluginlibrary.config.TaskerPluginConfigHelperNoOutputOrInput;
import com.joaomgcd.taskerpluginlibrary.input.TaskerInput;

import org.jetbrains.annotations.NotNull;

public class ReplierHelper extends TaskerPluginConfigHelperNoOutputOrInput {

    public ReplierHelper(@NonNull TaskerPluginConfig config) {
        super(config);
    }

    public Class getRunnerClass() {
        return ReplierRunner.class;
    }

    @Override
    public void addToStringBlurb(@NotNull TaskerInput input, @NotNull StringBuilder blurbBuilder) {
        blurbBuilder.append("Will send a message to the selected app for the selected contact");
    }

}
