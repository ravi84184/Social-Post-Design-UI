package com.nikdemo.socialpostdesignui.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.nikdemo.socialpostdesignui.R;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class Utilities {
    private static final String TAG = "Utilities";
    public static int MENU_SEARCH = 1;
    public static int MENU_CART = 2;
    //convert digit 1000 to 1k
    private static char[] charDgt = new char[]{'k', 'm', 'b', 't'};

    //checking internet connected or not
    public static boolean isNetworkAvaliable(Context ctx) {
        ConnectivityManager connectivityManager = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return (connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE) != null && connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED)
                || (connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI) != null && connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .getState() == NetworkInfo.State.CONNECTED);
    }

    public static void toastShow(Context mContext, String message) {
    /*    LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View toastView = inflater.inflate(R.layout.toast_layout, null);
        TextView textView = toastView.findViewById(R.id.txt_toast);
        textView.setText(message);
        // Initiate the Toast instance.
        Toast toast = new Toast(mContext);
        // Set custom view in toast.
        toast.setView(toastView);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();*/
//        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    // check enter email is valid or not
    public static boolean isValidEmailId(String email) {
        return !TextUtils.isEmpty(email) &&
                android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static void setTitle(final Context context, String title, Fragment fragment) {
        /*Toolbar toolbarTop = ((Activity) context).findViewById(R.id.actionbar);
        TextView tv_title = ((Activity) context).findViewById(R.id.tv_title);
        ImageView img_home = ((Activity) context).findViewById(R.id.img_home);

        img_home.setOnClickListener(view -> {
            MenuClickListener updateMyEventsInterface =
                    (MenuClickListener) fragment;
            updateMyEventsInterface.onMenuClick(MENU_SEARCH);
        });

        tv_title.setText(title);*/
    }

    // hide keyboard if showing activity
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)
                activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View focusedView = activity.getCurrentFocus();
        if (focusedView != null) {
            inputMethodManager.hideSoftInputFromWindow
                    (activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public static String encodeTobase64(String path) {
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        Bitmap btmUser = BitmapFactory.decodeFile(path, bmOptions);
        btmUser = Bitmap.createScaledBitmap(btmUser, 400,
                400, true);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        btmUser.compress(Bitmap.CompressFormat.PNG, 70, baos);
        btmUser.setDensity(100);

        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
        return imageEncoded;
    }

    /**
     * Recursive implementation, invokes itself for each factor of a thousand, increasing the class on each invokation.
     *
     * @param n         the number to format
     * @param iteration in fact this is the class from the array c
     * @return a String representing the number n formatted in a cool looking way.
     */
    public static String coolFormat(double n, int iteration) {
        if (n > 1000) {
            double d = ((long) n / 100) / 10.0;
            boolean isRound = (d * 10) % 10 == 0;//true if the decimal part is equal to 0 (then it's trimmed anyway)
            return (d < 1000 ? ((d > 99.9 || isRound || (!isRound && d > 9.99) ? (int) d * 10 / 10 : d + "") +
                    "" + charDgt[iteration]) : coolFormat(d, iteration + 1));
        } else {
            return "" + (long) n;
        }
    }

    public static String getCurrencyFormat(double value) {
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.getDefault());
       /* nf.setMaximumIntegerDigits(3);
        nf.setMinimumIntegerDigits(1);*/
        nf.setGroupingUsed(true);

        return "₹ " + nf.format(value);
    }

    public static String getCompleteAddressString(Context mContext, double LATITUDE,
                                                  double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE,
                    1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder();
                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append(" ");
                }
                strAdd = strReturnedAddress.toString();
                Log.w("My Current address", strReturnedAddress.toString());
            } else {
                Log.w("My Current address", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("My Current address", "Cannot get Address!");
        }
        return strAdd;
    }

    public static String getPath(Context mContext, Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = ((Activity) mContext).managedQuery(uri, projection, null,
                null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    public static String getByteArrayFromImageURL(String url) {

        try {
            URL imageUrl = new URL(url);
            URLConnection ucon = imageUrl.openConnection();
            InputStream is = ucon.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int read = 0;
            while ((read = is.read(buffer, 0, buffer.length)) != -1) {
                baos.write(buffer, 0, read);
            }
            baos.flush();
            return Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
        } catch (Exception e) {
            Log.d("Error", e.toString());
        }
        return null;
    }

    /**
     * Get a diff between two dates
     *
     * @param date the Server date
     * @return the diff value, in the days
     */
    @TargetApi(Build.VERSION_CODES.O)
    public static String getDateDiff(String date) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Calendar cal = Calendar.getInstance();
            cal.setTime(format.parse(date));

            LocalDate pdate = LocalDate.of(cal.get(Calendar.YEAR),
                    (cal.get(Calendar.MONTH) + 1), cal.get(Calendar.DAY_OF_MONTH));
            LocalDate now = LocalDate.of(Calendar.getInstance().get(Calendar.YEAR),
                    (Calendar.getInstance().get(Calendar.MONTH) + 1),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

            Period diff = Period.between(now, pdate);

            if (diff.getMonths() > 0) {
                if (diff.getDays() > 0) {
                    return diff.getMonths() + " months " + diff.getDays() + " days";
                } else {
                    return diff.getMonths() + " months";
                }
            } else if (diff.getDays() > 0) {
                return diff.getDays() + " days";
            } else {
                return "Today";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Today";
        }
    }

    /**
     * convert local date to gmt date
     *
     * @return String gmtDate
     */
    public static String localDateToGMTDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        SimpleDateFormat dateFormatSending = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date convertedDate = null;
        String gmtDate = null;
        try {
            convertedDate = dateFormat.parse(dateString);
            TimeZone gmtTime = TimeZone.getTimeZone("GMT");
            dateFormatSending.setTimeZone(gmtTime);
            gmtDate = dateFormatSending.format(convertedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return gmtDate;
    }

    /**
     * convert gmt time to local time
     *
     * @return local time
     */
    public static String convertGMTToLocal(String strtDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        TimeZone gmtTime = TimeZone.getTimeZone("GMT");
        dateFormat.setTimeZone(gmtTime);
        Date convertedSDate = new Date();
        try {
            convertedSDate = dateFormat.parse(strtDate);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();
        SimpleDateFormat dateFormatterGMT = new SimpleDateFormat("hh:mm a");
        dateFormatterGMT.setTimeZone(tz);
        return dateFormatterGMT.format(convertedSDate);
    }

    /**
     * convert gmt time to local time for space format
     *
     * @return local time
     */
    public static String convertGMTToLocalSpace(String strtDate) {

        SimpleDateFormat dateSpaceFormat = new SimpleDateFormat("dd MMM yyyy hh:mm a");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String strDate = null;
        try {
            Date date = dateSpaceFormat.parse(strtDate);
            strDate = dateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        TimeZone gmtTime = TimeZone.getTimeZone("GMT");
        dateFormat.setTimeZone(gmtTime);
        Date convertedSDate = new Date();
        try {
            convertedSDate = dateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();
        SimpleDateFormat dateFormatterGMT = new SimpleDateFormat("dd-MMM-yyyy hh:mm a");
        dateFormatterGMT.setTimeZone(tz);
        return dateFormatterGMT.format(convertedSDate);
    }

    public static int convertPxDp(Context mContext, int sizeInDP) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, sizeInDP, mContext.getResources()
                        .getDisplayMetrics());
    }

    // date time differnce in string
    public static String printDateDifference(String startDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss a");
        /*TimeZone gmtTime = TimeZone.getTimeZone("GMT");
        dateFormat.setTimeZone(gmtTime);*/
        Date convertedSDate = new Date();
        try {
            convertedSDate = dateFormat.parse(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        /*TimeZone tz = cal.getTimeZone();*/
        SimpleDateFormat dateFormatterGMT = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss a");
        /*dateFormatterGMT.setTimeZone(tz);*/
        Date serverDate = null;
        Date endDate = null;
        try {
            serverDate = dateFormat.parse(dateFormatterGMT.format(convertedSDate));
            String s = dateFormat.format(new Date());
            endDate = dateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //milliseconds
        long different = endDate.getTime() - serverDate.getTime();

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        if (elapsedDays == 0) {
            if (elapsedMinutes == 0) {
                return "now";
            } else if (elapsedHours == 0) {
                return elapsedMinutes + " minutes ago";
            } else {
                return elapsedHours + " hours ago";
            }
        } else if (elapsedDays > 30 && elapsedDays < 365) {
            int months = Math.round(elapsedDays / 30);
            if (months == 1) {
                return months + " month ago";
            } else {
                return months + " months ago";
            }
        } else if (elapsedDays > 365) {
            int years = Math.round(elapsedDays / 365);
            if (years == 1) {
                return years + " year ago";
            } else {
                return years + " years ago";
            }
        } else {
            return elapsedDays + " days ago";
        }
    }

    // permission dialog
    public static void showDialogOK(Context c, String message,
                                    DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(c)
                .setTitle(c.getResources().getString(R.string.app_name))
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", okListener)
                .create()
                .show();
    }

    /**
     * convert gmt time to local time for space format
     *
     * @return local time
     */
    public static String convertGMTToLocalTime(String strtDate) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        TimeZone gmtTime = TimeZone.getTimeZone("GMT");
        dateFormat.setTimeZone(gmtTime);
        Date convertedSDate = new Date();
        try {
            convertedSDate = dateFormat.parse(strtDate);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();
        SimpleDateFormat dateFormatterGMT = new SimpleDateFormat("hh:mm a");
        dateFormatterGMT.setTimeZone(tz);
        return dateFormatterGMT.format(convertedSDate);
    }

    public static String changeDateFormat(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss a");
        SimpleDateFormat dateFormatSending = new SimpleDateFormat("dd-MMM-yyyy");
        Date convertedDate = null;
        String gmtDate = null;
        try {
            convertedDate = dateFormat.parse(dateString);
            gmtDate = dateFormatSending.format(convertedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return gmtDate;
    }

    public static String changeDateFormat(String dateString, String output) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dateFormatSending = new SimpleDateFormat(output);
        Date convertedDate = null;
        String gmtDate = null;
        try {
            convertedDate = dateFormat.parse(dateString);
            gmtDate = dateFormatSending.format(convertedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return gmtDate;
    }

    public static int getDMY(String dateString, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormatSending = new SimpleDateFormat(format);
        Date convertedDate = null;
        String gmtDate = "0";
        try {
            convertedDate = dateFormat.parse(dateString);
            gmtDate = dateFormatSending.format(convertedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (gmtDate.length() == 1) {
            gmtDate = "0" + gmtDate;
        }
        Log.e(TAG, "getDMY: " + Integer.parseInt(gmtDate));
        return Integer.parseInt(gmtDate);
    }

    public static String getTime(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss a");
        SimpleDateFormat dateFormatSending = new SimpleDateFormat("hh:mm a");
        Date convertedDate = null;
        String gmtDate = null;
        try {
            convertedDate = dateFormat.parse(dateString);
            gmtDate = dateFormatSending.format(convertedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return gmtDate;
    }

    public static String changeDateFormat(Date dateString) {
        SimpleDateFormat dateFormatSending = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
        Date convertedDate = dateString;
        String gmtDate = null;
        try {
            gmtDate = dateFormatSending.format(convertedDate);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return gmtDate;
    }

    public static String changeDateFormat(Date dateString, String formet) {
        SimpleDateFormat dateFormatSending = new SimpleDateFormat(formet);
        Date convertedDate = dateString;
        String gmtDate = null;
        try {
            gmtDate = dateFormatSending.format(convertedDate);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return gmtDate;
    }

    public static boolean dateCompareMM(String startDate, String endDate) {
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return sdfDate.parse(endDate).before(sdfDate.parse(startDate)) &&
                    !sdfDate.parse(startDate).equals(sdfDate.parse(endDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String parseDate(String time, String inputPattern, String outputPattern) {
        if (time == null || time.equalsIgnoreCase("")) {
            return "";
        }
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        Date date = null;
        String str = null;
        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        final Calendar cal = Calendar.getInstance();
        cal.set(date.getYear(), date.getMonth(), date.getDate());
        return str;
    }

    public static long beforeDate(String time, int day) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = inputFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        final Calendar cal = Calendar.getInstance();
        assert date != null;
        cal.setTime(date);
        cal.add(Calendar.DATE, day);
        long val = cal.getTime().getTime();
        return (val);
    }


    public static boolean isBeforeDate(String date) {
        String inputDatePattern = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(inputDatePattern);
        Date date11 = null;
        Date date22 = new Date();
        try {
            date11 = sdf.parse(date);
            return date11.compareTo(date22) < 0;
        } catch (ParseException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        }

    }

    public static Date getWeekStartDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
        c.add(Calendar.DAY_OF_MONTH, -dayOfWeek);
        Log.e(TAG, "getWeekStartDate: " + c.getTime());
        return c.getTime();
    }

    public static Date getWeekEndDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
        c.add(Calendar.DAY_OF_MONTH, -dayOfWeek);

        c.add(Calendar.DAY_OF_MONTH, 6);
        Log.e(TAG, "getWeekEndDate: " + c.getTime());
        return c.getTime();
    }
}
