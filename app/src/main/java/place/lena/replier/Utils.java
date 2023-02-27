package place.lena.replier;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;

import rikka.shizuku.Shizuku;
import rikka.shizuku.ShizukuProvider;
import rikka.sui.Sui;

public class Utils {

    public static final int NOT_ALIVE = -2;
    public static final int UNSUPPORTED_VERSION = -1;
    public static final int HAS_PERMISSION = 2;
    public static final int DENIED_INDEFINITELY = 0;
    public static final int REQUESTED = 1;

    public static int getPermission() {
        if (!Shizuku.pingBinder()) {
            return NOT_ALIVE;
        }
        if (Shizuku.isPreV11()) {
            return UNSUPPORTED_VERSION;
        }

        if (Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED) {
            return HAS_PERMISSION;
        } else if (Shizuku.shouldShowRequestPermissionRationale()) {
            return DENIED_INDEFINITELY;
        } else {
            // Request the permission
            Shizuku.requestPermission(1000);

            // we really don't care whether or not it gets granted.

            return REQUESTED;
        }
    }

    public static void getPermissionAndMessage(Context context) {

        switch (getPermission()) {
            case NOT_ALIVE:
            {
                Toast.makeText(context, (CharSequence) "Shizuku isn't running.", Toast.LENGTH_LONG).show();
                break;
            }
            case UNSUPPORTED_VERSION:
            {
                Toast.makeText(context, (CharSequence) "Install Shizuku > v11", Toast.LENGTH_LONG).show();
                break;
            }
            case DENIED_INDEFINITELY:
            {
                Toast.makeText(context, (CharSequence) "Permission denied, go into the Shizuku app and grant the permission", Toast.LENGTH_LONG).show();
                break;
            }
        }
    }

    public static boolean sendIntent(Context context, String packageName, String quickResponderClass, Uri uri, String message) {
        if (Shizuku.checkSelfPermission() != PackageManager.PERMISSION_GRANTED) {
            return false; // we cant do this shit without shizuku
        }
        Intent intent = new Intent();
        intent.setAction("android.intent.action.RESPOND_VIA_MESSAGE"); // this requires the special permission
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setData(uri);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        intent.setClassName(packageName, quickResponderClass);
        return context.startService(intent) != null;
    }

    public static boolean sendIntentSignal(Context context, String number, String message) {
        return sendIntent(context, "org.thoughtcrime.securesms"
                , ".service.QuickResponseService",
                Uri.parse("smsto:" + number), message);

    }
}
