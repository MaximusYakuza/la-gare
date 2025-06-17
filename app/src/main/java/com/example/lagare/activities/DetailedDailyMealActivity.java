 package com.example.lagare.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.lagare.R;
import com.example.lagare.adapters.DetailedDailyAdapter;
import com.example.lagare.models.DetailedDailyModel;

import java.util.ArrayList;
import java.util.List;

 public class DetailedDailyMealActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<DetailedDailyModel> detailedDailyModelList;
    DetailedDailyAdapter dailyAdapter;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_daily_meal);

        String type = getIntent().getStringExtra("type");

        recyclerView = findViewById(R.id.detailed_rec);
        imageView = findViewById(R.id.detailed_img);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        detailedDailyModelList = new ArrayList<>();
        dailyAdapter = new DetailedDailyAdapter(detailedDailyModelList);
        recyclerView.setAdapter(dailyAdapter);

        if (type != null && type.equalsIgnoreCase("desayuno")){

            imageView.setImageResource(R.drawable.breakfast);
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.fav1, "Desayuno 1", "Descripcion", "4.4","40", "10 a 9"));
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.fav2, "Desayuno 2", "Descripcion", "4.4","40", "10 a 9"));
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.fav3, "Desayuno 3", "Descripcion", "4.4","40", "10 a 9"));
            dailyAdapter.notifyDataSetChanged();
        }
        if (type != null && type.equalsIgnoreCase("almuerzo")){

            imageView.setImageResource(R.drawable.lunch);
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.ver1, "Almuerzo 1", "Descripcion", "4.4","40", "10 a 9"));
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.ver2, "Almuerzo 2", "Descripcion", "4.4","40", "10 a 9"));
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.ver3, "Almuerzo 3", "Descripcion", "4.4","40", "10 a 9"));
            dailyAdapter.notifyDataSetChanged();
        }

        if (type != null && type.equalsIgnoreCase("cena")){

            imageView.setImageResource(R.drawable.dinner);
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.cena1, "Cena 1", "Descripcion", "4.4","40", "10 a 9"));
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.cena2, "Cena 2", "Descripcion", "4.4","40", "10 a 9"));
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.cena3, "Cena 3", "Descripcion", "4.4","40", "10 a 9"));
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.cena4, "Cena 4", "Descripcion", "4.4","40", "10 a 9"));
            dailyAdapter.notifyDataSetChanged();
        }


        if (type != null && type.equalsIgnoreCase("dulces")){

            imageView.setImageResource(R.drawable.sweets);
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.s1, "Dulce 1", "Descripcion", "4.4","40", "10am a 9pm"));
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.s2, "Dulce 2", "Descripcion", "4.4","40", "10am a 9pm"));
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.s3, "Dulce 3", "Descripcion", "4.4","40", "10am a 9pm"));
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.s4, "Dulce 4", "Descripcion", "4.4","40", "10am a 9pm"));
            dailyAdapter.notifyDataSetChanged();

        }

        if (type != null && type.equalsIgnoreCase("cafe")){

            imageView.setImageResource(R.drawable.coffe);
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.cafe1, "Cafe 1", "Descripcion", "4.4","40", "10 a 9"));
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.cafe2, "Cafe 2", "Descripcion", "4.4","40", "10 a 9"));
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.cafe3, "Cafe 3", "Descripcion", "4.4","40", "10 a 9"));
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.cafe4, "Cafe 4", "Descripcion", "4.4","40", "10 a 9"));
            dailyAdapter.notifyDataSetChanged();
        }
    }
}