package org.xbmc.fakemailer.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import org.xbmc.fakemailer.Activity.MaildetailsActivity;
import org.xbmc.fakemailer.Model.DataModel;
import org.xbmc.fakemailer.R;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.UserViewHolder> implements Filterable {
    private Context mContext;
    String username,domain;
    private int lastPosition = -1;
    public ArrayList<DataModel> listData, listFiltered;
    public DataAdapter(Context mContext, ArrayList<DataModel> listData,String username,String domain) {
        this.listData = listData;
        this.listFiltered = listData;
        this.mContext = mContext;
        this.username = username;
        this.domain = domain;
    }
    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from ( parent.getContext ( ) )
                .inflate ( R.layout.list_design, parent, false );
        return new UserViewHolder ( itemView );
    }
    public void setData(ArrayList<DataModel> data, String userName,String domaiin) {
        listFiltered.addAll (data);
        username = userName;
        domain  = domaiin;
        notifyDataSetChanged ( );
    }
    @Override
    public void onBindViewHolder(final UserViewHolder holder, final int position) {
        String[] parts = listFiltered.get ( position ).getFrom ( ).split("\\&");
        String user_name = parts[0];
        String AfterFirstsign = parts[1];
        String email_id = AfterFirstsign.substring(3);

        holder.txt_user_name.setText (user_name );
        holder.txt_mail_id.setText ( email_id);
        holder.txt_date.setText ( listFiltered.get ( position ).getDate ( ) );
        holder.txt_subj.setText ( listFiltered.get ( position ).getSubject ( ) );
        holder.linear.setOnClickListener ( view -> {
            setAnimation ( holder.itemView, position );
            Intent intent = new Intent ( mContext, MaildetailsActivity.class );
            intent.putExtra ( "mailid", listFiltered.get ( position ).getMail_id ( ) );
            intent.putExtra ( "username",username);
            intent.putExtra ( "domain", domain);
            mContext.startActivity ( intent );
        } );

        setAnimation ( holder.itemView, position );
    }
    public void delete(){
        listFiltered.clear ();
        listData.clear ();
        notifyDataSetChanged ();
    }
    private void setAnimation(View itemView, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation ( mContext, R.anim.slide_in );
            itemView.startAnimation ( animation );
            lastPosition = position;
        }
    }
    @Override
    public Filter getFilter() {
        return new Filter ( ) {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString ( );
                if (charString.isEmpty ( )) {
                    listFiltered = listData;
                } else {
                    ArrayList<DataModel> filteredList = new ArrayList<> ( );
                    for (DataModel row : listData) {
                        String[] parts = row.getFrom ( ).split("\\&");
                        if (parts[0].toLowerCase ().contains( charString.toLowerCase ( ) )) {
                            filteredList.add ( row );
                        }
                    }
                    listFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults ( );
                filterResults.values = listFiltered;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listFiltered = (ArrayList<DataModel>) filterResults.values;
                notifyDataSetChanged ( );
            }
        };
    }
    @Override
    public int getItemCount() {
        return  listFiltered == null ? 0 : listFiltered.size();
    }
    /**
     ViewHolder class
     */
    public class UserViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_user_name,txt_date,txt_mail_id,txt_subj;
        LinearLayout linear;
        public UserViewHolder(View view) {
            super ( view );
            txt_user_name = view.findViewById ( R.id.txt_user_name );
            txt_mail_id = view.findViewById ( R.id.txt_mail_id );
            txt_date = view.findViewById ( R.id.txt_date );
            linear = view.findViewById ( R.id.linear );
            txt_subj = view.findViewById ( R.id.txt_subj );
        }
    }
}
