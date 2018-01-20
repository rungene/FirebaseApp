package net.simplifiedcoding.FirebaseApp;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //recyclerview object
    private RecyclerView recyclerView;

    //adapter object
    private RecyclerView.Adapter adapter;

    //database reference
    private DatabaseReference mDatabase;

    //progress dialog
    private ProgressDialog progressDialog;

    //list to hold all the uploaded images
    private List<Upload> uploads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_images);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        progressDialog = new ProgressDialog(this);

        uploads = new ArrayList<>();

        //displaying progress dialog while fetching images
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //adding an event listener to fetch values
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //dismissing the progress dialog
                progressDialog.dismiss();

                //iterating through all the values in database
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    uploads.add(upload);
                }
                //creating adapter
                adapter = new MyAdapter(getApplicationContext(), uploads);

                //adding adapter to recyclerview
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });

    }
}

/*
public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef ;
   private  RecyclerView mBlogList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Recycler view
        mBlogList = (RecyclerView) findViewById(R.id.blog_list);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));

        //send a Query to the DB
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("recyclerfirebaseimages");


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<ModelClass,BlogViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<ModelClass,BlogViewHolder>(
                        ModelClass.class,
                        R.layout.design_row,
                        BlogViewHolder.class,
                        myRef) {
                    @Override
                    protected void populateViewHolder(BlogViewHolder viewHolder,ModelClass model,int position){
                        viewHolder.setTitle(model.getFirst_name());
                        viewHolder.setImage(getApplicationContext(), model.getAvanta());

                    }


                };
                mBlogList.setAdapter(firebaseRecyclerAdapter);

        //view Holder for Recycler view


    }
    public static class BlogViewHolder extends RecyclerView.ViewHolder{
        View mView;

        public BlogViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://robohash.org/")
                    );
                    Intent browserChooserIntent = Intent.createChooser(browserIntent,"Choose browser of your choice");
                    v.getContext().startActivity(browserChooserIntent);
                }
            });
        }
        public void setTitle(String title){
            TextView post_title = (TextView) mView.findViewById(R.id.titleText);

        }
        public void setImage (Context ctx,String image){
            ImageView post_image = (ImageView) mView.findViewById(R.id.imageViewy);

            //we need to pass the context
            Picasso.with(ctx).load(image).into(post_image);

        }

    }
}*/
