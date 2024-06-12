package com.example.trade_mentor;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StockAdapter extends RecyclerView.Adapter<StockAdapter.ViewHolder> {

     static List<StockItem> mData;

    public StockAdapter(@Nullable List<StockItem> data) {
        mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stock_by_sell_recycle_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StockItem item = mData.get(position);
        try {
            holder.nameTextView1.setText("Bank = "+item.getCompanyName());
            holder.stockPriceTextView1.setText("Price = "+item.getStockPrice());
            holder.stockChpTextView1.setText("chp = "+item.getchp()+"%");
            holder.stockNumberTextView1.setText("Number Of Stock = "+item.getStockNumber());
            holder.TotalAmountTextView1.setText("Total Amoun = "+item.gettotalamount());
            holder.dateTextView1.setText("Date Of Buy Stock = "+item.getDate());
        }
        catch (NullPointerException e){
            Toast.makeText(holder.itemView.getContext(), "null pointer", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void removeItem(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView nameTextView1;
        TextView stockPriceTextView1;
        TextView stockChpTextView1;
        TextView stockNumberTextView1;
        TextView TotalAmountTextView1;
        TextView dateTextView1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView1 = itemView.findViewById(R.id.companyNameTextView);
            stockPriceTextView1 = itemView.findViewById(R.id.stockPriceTextView);
            stockChpTextView1 = itemView.findViewById(R.id.stockChpTextView);
            stockNumberTextView1 = itemView.findViewById(R.id.stockNumberTextView);
            TotalAmountTextView1 = itemView.findViewById(R.id.TotalAmountTextView);
            dateTextView1 = itemView.findViewById(R.id.dateTextView);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
//            int position=getAdapterPosition();
//            String name=mData.get(position).getCompanyName();
//            Toast.makeText(v.getContext(),"hello  "+name,Toast.LENGTH_SHORT).show();
//            String price=mData.get(position).getStockPrice();
//            String chp=mData.get(position).getchp();
//            Intent i=new Intent(v.getContext(),stock_details.class);
//            i.putExtra("bank_name",name);
//            i.putExtra("lp",price);
//            i.putExtra("chp",chp);
//            v.getContext().startActivity(i);
        }
    }
}
