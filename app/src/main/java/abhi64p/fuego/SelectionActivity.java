package abhi64p.fuego;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SelectionActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        View v1 = findViewById(R.id.ManualMode);
        View v2 = findViewById(R.id.Burner1);
        View v3 = findViewById(R.id.Burner2);

        v1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(SelectionActivity.this,BurnerNobActivity.class));
            }
        });

        v2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent ModeIntent  = new Intent(SelectionActivity.this,ModeActivity.class);
                ModeIntent.putExtra("BurnerText","Burner 1");
                startActivity(ModeIntent);
            }
        });

        v3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent ModeIntent  = new Intent(SelectionActivity.this,ModeActivity.class);
                ModeIntent.putExtra("BurnerText","Burner 2");
                startActivity(ModeIntent);
            }
        });
    }
}
