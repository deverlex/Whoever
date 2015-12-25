package vn.whoever;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import com.facebook.FacebookSdk;

import java.util.ArrayList;

import vn.whoever.fragments.SignInFragment;

/**
 * Created by spider man on 12/22/2015.
 * TODO: login and logout application
 */
public class StartActivity extends AppCompatActivity {

    public static final String PREFERENCES = "PREFERENCES_LOCAL";
    public static SharedPreferences sharedPreferences;

  //  private LoginButton loginButtonFaceBook;
  //  private CallbackManager callbackManagerFacebook;
  //  private ProfileTracker profileTrackerFaceBook;

    private FragmentManager frgStartManager;
    private FragmentTransaction frgStartTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   FacebookSdk.sdkInitialize(getApplicationContext());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        sharedPreferences = getSharedPreferences(PREFERENCES, MODE_PRIVATE);
        boolean isLogin = sharedPreferences.getBoolean("isLogin", false);
        if(isLogin) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        setContentView(R.layout.start_main);

        TextView logoText = (TextView) findViewById(R.id.logoTextStart);
        Typeface bauhau93_font = Typeface.createFromAsset(getAssets(), "fonts/bauhau93.ttf");
        logoText.setTypeface(bauhau93_font);

        frgStartManager = getFragmentManager();
        frgStartTransaction = frgStartManager.beginTransaction();
        frgStartTransaction.replace(R.id.layoutStartApp, new SignInFragment()).commit();



        AccountManager am = AccountManager.get(this);
        Account[] accounts = am.getAccounts();
        ArrayList<String> googleAccounts = new ArrayList<String>();
        for (Account ac : accounts) {
            String acname = ac.name;
            String actype = ac.type;
            //add only google accounts
            if(ac.type.equals("com.google")) {
                googleAccounts.add(ac.name);
            }
            Log.d("Account", "accountInfo: " + acname + ":" + actype);
        }

        /*
        callbackManagerFacebook = CallbackManager.Factory.create();

        loginButtonFaceBook = (LoginButton) findViewById(R.id.loginFaceBookButton);

        loginButtonFaceBook.setReadPermissions(Arrays.asList("email, public_profile"));
        loginButtonFaceBook.registerCallback(callbackManagerFacebook, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                GraphRequest.newMeRequest(
                        loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject me, GraphResponse response) {


                                if (response.getError() != null) {

                                } else {
                                    String email = "";
                                    try {
                                        email = response.getJSONObject().getJSONObject("email").getJSONObject("data").getString("email");
                                        Log.e("Result Email: ", email);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                    email = me.optString("email");
                                    String id = me.optString("id");
                                    String name = me.optString("name");

                                    Log.d("NAME: ", name);
                                    Log.d("ID: ", id);
                                    Log.d("EMAIL: ", email);
                                }
                            }
                        }).executeAsync();
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });


        loginButtonFaceBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLoginFaceBook();
            }
        });
        */
    }

    /*
    public void getProfileTracker() {
        profileTrackerFaceBook = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile,
                                                   Profile currentProfile) {
                String fistName = currentProfile.getName();
                Log.d("Name of user: ", fistName);
            }
        };
    }

    public void setLoginFaceBook() {
        callbackManagerFacebook = CallbackManager.Factory.create();

        //List<String> permissionNeeds= Arrays.asList("email", "user_birthday", "user_friends");

        //LoginManager.getInstance().logInWithReadPermissions(this, permissionNeeds);
        LoginManager.getInstance().registerCallback(callbackManagerFacebook, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // not called
                Log.d("fb_login_sdk", "callback success");
                navigateToMain();
            }

            @Override
            public void onCancel() {
                // not called
                Toast toastLoginCancel = Toast.makeText(getApplicationContext(),
                        getString(R.string.login_cancel), Toast.LENGTH_SHORT);
                toastLoginCancel.show();
            }

            @Override
            public void onError(FacebookException e) {
                // not called
                Toast toastLoginError = Toast.makeText(getApplicationContext(),
                        getString(R.string.login_error), Toast.LENGTH_SHORT);
                toastLoginError.show();
            }
        });
    }

    */

    public void navigateToMain() {
        Intent intentMain = new Intent(this, MainActivity.class);
        startActivity(intentMain);
        finish();
    }


    /*
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManagerFacebook.onActivityResult(requestCode, resultCode, data);
    //    getProfileTracker();
    }

    */


    @Override
    protected void onResume() {
        super.onResume();
    //    AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    //    AppEventsLogger.deactivateApp(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
