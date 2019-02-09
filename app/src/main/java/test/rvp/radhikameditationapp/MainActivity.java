package test.rvp.radhikameditationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String IMGLINK = "image_link";
    public static final String MUSICLINK = "music_link";
    private static final String TAG = "MainActivity";
    RelativeLayout focus, calmDown, destress, relax;
    private DocumentReference documentReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

    }

    private void initViews() {
        focus = (RelativeLayout) findViewById(R.id.focus);
        calmDown = (RelativeLayout) findViewById(R.id.calm);
        destress = (RelativeLayout) findViewById(R.id.destre);
        relax = (RelativeLayout) findViewById(R.id.relax);
        focus.setOnClickListener(this);
        calmDown.setOnClickListener(this);
        destress.setOnClickListener(this);
        relax.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.focus:
                documentReference = FirebaseFirestore.getInstance().document("meditation/focus/session/random_document_id");
                getImgAndMusic();
                break;
            case R.id.calm:
                documentReference = FirebaseFirestore.getInstance().document("meditation/calm_down/session/random_document_id");
                getImgAndMusic();
                break;
            case R.id.destre:
                documentReference = FirebaseFirestore.getInstance().document("meditation/destress/session/random_document_id");
                getImgAndMusic();
                break;
            case R.id.relax:
                documentReference = FirebaseFirestore.getInstance().document("meditation/relax/session/random_document_id");
                getImgAndMusic();
                break;
        }
    }

    private void getImgAndMusic() {
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    String imgLink = documentSnapshot.getString("imageLink");
                    String musicLink = documentSnapshot.getString("link");

                    Intent intent = new Intent(MainActivity.this, MeditationActivity.class);
                    intent.putExtra(IMGLINK, imgLink);
                    intent.putExtra(MUSICLINK, musicLink);
                    startActivity(intent);
                }
            }
        });
    }


}
