package com.developeralamin.sql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.developeralamin.sql.databinding.ActivityMainBinding;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fname = binding.inputFirstName.getText().toString().trim();
                String lname = binding.inputLastName.getText().toString().trim();
                String email = binding.inputEmail.getText().toString().trim();

                if (fname.isEmpty()){
                    binding.inputFirstName.setError("Enter your name");
                    binding.inputFirstName.requestFocus();
                    return;
                }if (lname.isEmpty()){
                    binding.inputLastName.setError("Enter your lasname");
                    binding.inputLastName.requestFocus();
                    return;
                }if (email.isEmpty()){
                    binding.inputEmail.setError("Enter your Email");
                    binding.inputEmail.requestFocus();
                    return;
                }

                Call<ResponseBody> call = RetrofitClient
                        .getInstance()
                        .getApi()
                        .createUser(fname,lname,email);

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        Toast.makeText(MainActivity.this, "Data Insert Successfully", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                        Toast.makeText(MainActivity.this, t.getLocalizedMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
}