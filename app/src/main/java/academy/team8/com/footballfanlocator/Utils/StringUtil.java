package academy.team8.com.footballfanlocator.Utils;

import android.text.TextUtils;

public class StringUtil {
    public static boolean isNullOrEmpty(String string){
        return string == null || TextUtils.isEmpty(string);
    }
}
