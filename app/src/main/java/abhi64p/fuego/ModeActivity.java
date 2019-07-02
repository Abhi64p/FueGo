package abhi64p.fuego;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ModeActivity extends AppCompatActivity
{
    private String BurnerText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);

        BurnerText = getIntent().getStringExtra("BurnerText");
        final TextView TitleTV = findViewById(R.id.ModeActivityTitleTV);
        TitleTV.setText(String.format("%s %s",BurnerText,"Modes"));
    }

    public void ModeClicked(View view)
    {
        String Text = "";
        switch (view.getId())
        {
            case R.id.Milk: Text = "Milk Mode"; break;
            case R.id.Saute: Text = "Saute Mode"; break;
            case R.id.Veg: Text = "Veg Cook Mode"; break;
        }

        Intent CookIntent = new Intent(this,CookingActivity.class);
        CookIntent.putExtra("BurnerText",BurnerText);
        CookIntent.putExtra("ModeText",Text);
        startActivity(CookIntent);
    }
}
