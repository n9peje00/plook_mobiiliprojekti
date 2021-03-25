package co.plook;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class DatabaseDownloader
{
    private FirebaseFirestore db;
    private CollectionReference collRef;
    private OnLoadedListener listener;

    public DatabaseDownloader()
    {
        db = FirebaseFirestore.getInstance();
    }

    //WIP logic that determines what the type of queried field is

    public void loadCollection(String collectionPath, String field, String[] query)
    {
        collRef = db.collection(collectionPath);
        Query q = collRef.whereArrayContainsAny(field, Arrays.asList(query));

        q.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task)
            {
                if (task.isSuccessful())
                {
                    listener.onLoaded(task.getResult());
                }
                else
                    listener.onFailure();
            }
        });
    }

    public void loadCollection(String collectionPath, String field, String query)
    {
        collRef = db.collection(collectionPath);
        Query q = collRef.whereArrayContains(field, query);

        q.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task)
            {
                if (task.isSuccessful())
                {
                    listener.onLoaded(task.getResult());
                }
                else
                    listener.onFailure();
            }
        });
    }

    public void loadCollection(String collectionPath)
    {
        db.collection(collectionPath)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
                {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task)
                    {
                        if (task.isSuccessful())
                        {
                            listener.onLoaded(task.getResult());
                        }
                        else
                            listener.onFailure();
                    }
                });
    }

    public interface OnLoadedListener
    {
        void onLoaded(QuerySnapshot documentSnapshots);
        void onFailure();
    }

    public void setOnLoadedListener(OnLoadedListener eventListener)
    {
        listener = eventListener;
    }


}
