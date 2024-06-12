package com.example.trade_mentor;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StockInfoAdapter extends RecyclerView.Adapter<StockInfoAdapter.ViewHolder> {
     List<StockInfo> stockInfoList;

    public StockInfoAdapter(List<StockInfo> stockInfoList) {
        this.stockInfoList = stockInfoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stock_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StockInfo stockInfo = stockInfoList.get(position);
        holder.nameTextView.setText(stockInfo.getName());
        holder.codeTextView.setText(stockInfo.getCode());
        holder.lpTextView.setText(String.valueOf(stockInfo.getLp()));
        holder.chTextView.setText(String.valueOf(stockInfo.getCh()));
        holder.chpTextView.setText(String.valueOf(stockInfo.getChp()));
    }

    @Override
    public int getItemCount() {
        return stockInfoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameTextView;
        TextView codeTextView;
        TextView lpTextView;
        TextView chTextView;
        TextView chpTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            codeTextView = itemView.findViewById(R.id.codeTextView);
            lpTextView = itemView.findViewById(R.id.lpTextView);
            chTextView = itemView.findViewById(R.id.chTextView);
            chpTextView = itemView.findViewById(R.id.chpTextView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();
            String name=stockInfoList.get(position).getName();
            Intent i=new Intent(v.getContext(),stock_details.class);
            i.putExtra("bank_name",name);
            i.putExtra("lp",stockInfoList.get(position).getLp());
            i.putExtra("chp",stockInfoList.get(position).getChp());
            v.getContext().startActivity(i);
        }
    }
}
