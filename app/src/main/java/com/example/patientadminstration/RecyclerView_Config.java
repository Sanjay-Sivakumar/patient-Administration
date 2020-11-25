package com.example.patientadminstration;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerView_Config {

    private Context mcontext;
    private MemberAdapter mMemberAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<Member> members, List<String> keys){
        mcontext = context;
        mMemberAdapter = new MemberAdapter(members, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mMemberAdapter);
    }

    class MemberItemView extends RecyclerView.ViewHolder{
        private TextView mName;
       // private TextView mID;
        private TextView mIVName;
        private TextView mIVInfusionRate;
        private TextView mIVReading;
        private TextView mBedNo;

        private String key;

        public MemberItemView(ViewGroup parent){
            super(LayoutInflater.from(mcontext).inflate(R.layout.member_list_item, parent, false));
            mName = (TextView) itemView.findViewById(R.id.tvPatientName);
            //mID = (TextView) itemView.findViewById(R.id.tvID);
            mIVName = (TextView) itemView.findViewById(R.id.tvIVName);
            mIVInfusionRate = (TextView) itemView.findViewById(R.id.tvIVInfusionRate);
            mIVReading = (TextView) itemView.findViewById(R.id.tvIVReading);
            mBedNo = (TextView) itemView.findViewById(R.id.txtBedNo);
        }

        @SuppressLint("SetTextI18n")
        public void bind(Member member, String key){
            mName.setText(member.getName());
           // mID.setText(member.getId());
            mIVName.setText("IVName:" + member.getIVname());
            mIVInfusionRate.setText("InfusionRate:" +member.getIVrate());
            mIVReading.setText(member.getIVreading());
            mBedNo.setText( " Bed #" + member.getBedNo());
            this.key = key;
        }

    }


    class MemberAdapter extends RecyclerView.Adapter<MemberItemView>{
        private List<Member> mMemberList;
        private List<String> mKeys;

        public MemberAdapter(List<Member> mMemberList, List<String> mKeys) {
            this.mMemberList = mMemberList;
            this.mKeys = mKeys;
        }

        public List<Member> getmMemberList() {
            return mMemberList;
        }

        public void setmMemberList(List<Member> mMemberList) {
            this.mMemberList = mMemberList;
        }

        @NonNull
        @Override
        public MemberItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MemberItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull MemberItemView holder, int position) {
            holder.bind(mMemberList.get(position), mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mMemberList.size();
        }
    }
}
