package endless.syria.sychat.Utils.Models.Adapters;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import endless.syria.sychat.R;
import endless.syria.sychat.Utils.Models.CircleImageView;
import endless.syria.sychat.Utils.Models.MessageText;

public class ChatAdapter extends RecyclerView.Adapter {
    private ArrayList<MessageText> arrayList;
    private String my = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
    public ChatAdapter(ArrayList<MessageText> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0){
            return new MyMessageHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mymessage_adapter,parent,false));
        }else {
            return new UserMessageHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.usermessage_adapter,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MessageText messages = arrayList.get(position);
        switch (holder.getItemViewType()){
            case 0 :
                ((MyMessageHolder) holder).bind(messages);
                break;
            case 1 :
                ((UserMessageHolder) holder).bind(messages);
                break;
            default:
        }
    }

    @Override
    public int getItemViewType(int position) {
        MessageText messageText = arrayList.get(position);
            return messageText.getType();
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    static class MyMessageHolder extends RecyclerView.ViewHolder{
        TextView body,time;
        public MyMessageHolder(@NonNull View itemView) {
            super(itemView);
            body = itemView.findViewById(R.id.cmtext_message_body);
            time = itemView.findViewById(R.id.cmtext_message_time);
        }
        public void bind(MessageText messages){
            body.setText(messages.getMessage());
            time.setText(messages.getTime());
        }
    }
    static class UserMessageHolder extends RecyclerView.ViewHolder{
        CircleImageView imageView;
        TextView name,body,time;
        public UserMessageHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cuimage_message_profile);
           // name = itemView.findViewById(R.id.cutext_message_name);
            body = itemView.findViewById(R.id.cutext_message_body);
            time = itemView.findViewById(R.id.cutext_message_time);
        }
        public void bind(MessageText messages) {
           // name.setText(messages.getUser());
            body.setText(messages.getMessage());
            time.setText(messages.getTime());
            imageView.setImageURI(Uri.parse("sdcard/iData/users/" + messages.getUser() + "/" + messages.getUser() + ".jpg"));
        }
    }
}
