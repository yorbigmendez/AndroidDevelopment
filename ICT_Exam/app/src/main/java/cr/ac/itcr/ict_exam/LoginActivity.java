package cr.ac.itcr.ict_exam;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;
import access_data.UserRepository;
import class_structures.User;

public class LoginActivity extends AppCompatActivity {
    //Variables used in activity
    private UserRepository ur;
    private EditText txtUser;
    private EditText txtPass;
    private Button btnLogin;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Get the widgets from view
        txtUser = (EditText)findViewById(R.id.txtEmail);
        txtPass = (EditText)findViewById(R.id.txtPassword);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnRegister = (Button)findViewById(R.id.btnRegister);
        //Create instance to manage user repository
        ur = new UserRepository(getApplicationContext());
        //Add onClick action to the button
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logIn();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    //Checks from the list of users of the username and pass is valid
    public void logIn(){
        boolean logged = false;
        if(txtUser.getText().toString().equals("") || txtPass.getText().equals(""))
            Toast.makeText(LoginActivity.this, "Empty spaces exist", Toast.LENGTH_SHORT).show();
        ArrayList<User> userList = ur.GetBy(new User(txtUser.getText().toString(),txtPass.getText().toString()));
        if(userList.size() == 0) {//No user exists with that info
            Toast.makeText(LoginActivity.this, "Invalid user or pass", Toast.LENGTH_SHORT).show();
        }else{
            //Check the list see which one hast the same username and password
            for(int i = 0; i <userList.size();i++){
                if(userList.get(i) != null) {
                    logged = true;
                    if (userList.get(i).getName().equals(txtUser.getText().toString()) && userList.get(i).getPass().equals(txtPass.getText().toString())) {//Credentials are equal
                        Intent x = new Intent(getApplicationContext(), ScreenSlideActivity.class);
                        startActivity(x);
                        break;
                    }
                }
            }
            //Not logged in, password invalid
            if(!logged) {
                Toast.makeText(LoginActivity.this, "Invalid user or pass", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //Registers a user, first checks existence then adds user
    public void registerUser(){
        ArrayList<User> arrayUsers;
        boolean registered = false;
        if(txtUser.getText().toString().equals("") || txtPass.getText().toString().equals("")){
            Toast.makeText(LoginActivity.this, "Empty spaces exist", Toast.LENGTH_SHORT).show();
        }else{//Not empty spaces
            arrayUsers = ur.GetBy(new User(txtUser.getText().toString(),txtPass.getText().toString()));//Checks existence of username first
            if(arrayUsers.size() == 0){
                ur.Save(new User(txtUser.getText().toString(),txtPass.getText().toString()));//Add user
                Toast.makeText(LoginActivity.this, "User created succesfully", Toast.LENGTH_SHORT).show();
                Intent x = new Intent(getApplicationContext(), ScreenSlideActivity.class);//invoke activity
                startActivity(x);
            }
            if(!registered){
                Toast.makeText(LoginActivity.this, "Invalid user or pass for registering, size: "+arrayUsers.size(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
