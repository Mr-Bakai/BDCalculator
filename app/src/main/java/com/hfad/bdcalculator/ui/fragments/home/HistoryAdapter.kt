package com.hfad.bdcalculator.ui.fragments.home
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hfad.bdcalculator.data.local.room.History
import com.hfad.bdcalculator.databinding.ItemHistoryBinding

class HistoryAdapter(
    private val history: List<History>,
    private val listener: OnHistory
) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            ItemHistoryBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }

    override fun getItemCount() = history.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(history[position])
        holder.itemView.setOnClickListener {
            listener.onHistoryClick(history[position])
        }
    }

    class ViewHolder(private val view: ItemHistoryBinding) : RecyclerView.ViewHolder(view.root) {

        @SuppressLint("SetTextI18n")
        fun onBind(history: History) {
            view.textTypedOnes.text = history.typedOnes
            view.textResult.text = "=${history.result}"
        }
    }
}

interface OnHistory{
    fun onHistoryClick(history: History)
}