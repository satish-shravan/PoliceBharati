package mk.news.policebharati;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

import static android.content.Context.CLIPBOARD_SERVICE;

public class Adapter extends RecyclerView.Adapter<Adapter.Myholder> {

    Context context;
    List<QNA_Data> Data;

    public  static  int lastPosition = -1;

    public Adapter(Context context, List<QNA_Data> Data)
    {
        this.context = context;
        this.Data = Data;
    }

    public Adapter() {

    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from ( context ).inflate (R.layout.custom_qna_item,parent,false );
        Myholder myholder=new Myholder (view);

        return myholder;
    }

    @Override
    public void onBindViewHolder(@NonNull final Myholder holder, final int position) {

       holder.que.setText ( Data.get ( position ).getQuestion () );
       holder.ans.setText ( Data.get ( position ).getAnswer () );

       setAnimation ( holder.ans.getRootView (),position );

        holder.mcopy.setOnClickListener ( new View.OnClickListener ()
        {
            String que=Data.get ( position).getQuestion ();
            String ans=Data.get(position).getAnswer ();
            @Override
            public void onClick(View v) {

                ClipboardManager clipboard=(ClipboardManager) context.getSystemService (CLIPBOARD_SERVICE);
                ClipData clip= (ClipData) ClipData.newPlainText ( "text", que+"\n\n"+ans);
                clipboard.setPrimaryClip ( clip );

                Toast.makeText ( context, "Text Copied", Toast.LENGTH_SHORT ).show ();
            }
        });
        holder.mshare.setOnClickListener ( new View.OnClickListener ()
        {

            @Override
            public void onClick(View v) {
                Intent share=new Intent ( Intent.ACTION_SEND );
                share.setType ( "text/plain" );
                String sub="Subject";
                // share.putExtra ( Intent.EXTRA_SUBJECT,mcontext );
               // share.putExtra ( Intent.EXTRA_TEXT, Data.get ( position ).getQuestion ());
                String que=Data.get ( position ).getQuestion ();
                share.putExtra ( Intent.EXTRA_TEXT, que+"\n\n"+Data.get ( position ).getAnswer ());
                share.setFlags ( Intent.FLAG_ACTIVITY_NEW_TASK );
                context.startActivity ( share );
                Toast.makeText ( context, "Thank You !!", Toast.LENGTH_SHORT ).show ();
            }
        });

        holder.mwhatapp.setOnClickListener ( new View.OnClickListener ()
        {

            @Override
            public void onClick(View v) {
                try {
                    Intent share = new Intent ( Intent.ACTION_SEND );
                    share.setType ( "text/plain" );
                    share.setPackage ( "com.whatsapp" );
                    String sub = "Subject";
                    String que=Data.get ( position ).getQuestion ();
                    share.putExtra ( Intent.EXTRA_TEXT, que+"\n\n"+Data.get ( position ).getAnswer ());
                    share.setFlags ( Intent.FLAG_ACTIVITY_NEW_TASK );
                    context.startActivity ( share );
                    Toast.makeText ( context, " Whatsapp ", Toast.LENGTH_SHORT ).show ();

                }
                catch(Exception e)
                {
                    Toast.makeText ( context,"Whatsapp is not Installed " +"\n\t" ,Toast.LENGTH_SHORT).show ();
                   // Snackbar snackbar;
                    //snackbar = Snackbar.make(holder.que.getRootView (), "Please Install WhatsApp", Snackbar.LENGTH_LONG);
                    //snackbar.show();


                }
            }
        });

    }

    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation ( context, android.R.anim.slide_in_left );
            viewToAnimate.startAnimation ( animation );
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return Data.size ();
    }

    public static class Myholder extends RecyclerView.ViewHolder
    {
        TextView que,ans;
        LinearLayout linearLayout;
        ImageView mcopy,mshare,mwhatapp;


        public Myholder(@NonNull View itemView) {
            super ( itemView );
            que=itemView.findViewById ( R.id.question );
            ans=itemView.findViewById ( R.id.answer ) ;
            linearLayout=itemView.findViewById ( R.id.linear_layout );
            mcopy=itemView.findViewById ( R.id.mcopy );
            mshare=itemView.findViewById ( R.id.share);
            mwhatapp = itemView.findViewById ( R.id.mwhatsapp );


        }
    }
}
