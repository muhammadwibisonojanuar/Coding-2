package id.kotlin.belajar.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.kotlin.belajar.R
import id.kotlin.belajar.data.Result
import id.kotlin.belajar.presentation.HomeAdapter.HomeViewHolder


class HomeAdapter(private val results: List<Result>): RecyclerView.Adapter<HomeViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(
                    R.layout.item_home,
                    parent,
                    false
                )
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int){
        holder.bind(results[holder.adapterPosition])
    }
    override fun getItemCount(): Int{
        return results.count()
    }

    inner class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(result: Result){
            with(itemView) {
                val title = findViewById<TextView>(R.id.original_title)
                title.text = result.title

                val overview = findViewById<TextView>(R.id.overview)
                overview.text = result.overview
            }
        }

    }
}