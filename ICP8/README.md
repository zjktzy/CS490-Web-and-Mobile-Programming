
# ICP 8

## General
- android:onClick was added to login/logout buttons
- invalid credentials text starts invisible but visibility is set to TRUE when invalid credentials are entered
- username: admin password: admin is the only valid login

### Validate Credentials Function

```javascript
public void validateCredentials(View v) {
        //gather user input
        EditText usernameInput = (EditText) findViewById(R.id.email);
        EditText passwordInput = (EditText) findViewById(R.id.pwd);
        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();

        boolean validationFlag = false;

        //if text boxes are populated, check if they are admin/admin
        if (!username.isEmpty() && !password.isEmpty()) {
            if (username.equals("admin") && password.equals("admin")) {
                validationFlag = true;
            }
        }

        //display error or navigate to HomeActivity
        if (!validationFlag) {
            // handle when validationflag is false
            TextView errorText = findViewById(R.id.errorText);
            errorText.setVisibility(View.VISIBLE);
        } else {
            // handle when validationflag is true
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        }
    }
```

### Log Out Function

```javascript
public void logOut(View v) {
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(intent);
    }
```

### Android Manifest 

```javascript
<application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        <activity android:name=".HomeActivity">
            <intent-filter>
                <action android:name="android.intent.action.DEFAULT" />

                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
        </activity>
    </application>
```
