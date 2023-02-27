package place.lena.replier;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.joaomgcd.taskerpluginlibrary.action.TaskerPluginRunnerActionNoOutputOrInput;
import com.joaomgcd.taskerpluginlibrary.input.TaskerInput;
import com.joaomgcd.taskerpluginlibrary.runner.TaskerPluginResult;
import com.joaomgcd.taskerpluginlibrary.runner.TaskerPluginResultSucess;

import org.jetbrains.annotations.NotNull;

public class ReplierRunner extends TaskerPluginRunnerActionNoOutputOrInput {
    @NotNull
    public TaskerPluginResult run(@NotNull final Context context, @NotNull TaskerInput input) {
        (new Handler(Looper.getMainLooper())).post(() ->
                Toast.makeText(context, (CharSequence) "Basic", Toast.LENGTH_LONG).show());

        return new TaskerPluginResultSucess();
    }
}
