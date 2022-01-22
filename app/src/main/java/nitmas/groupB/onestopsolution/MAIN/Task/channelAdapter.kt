package nitmas.groupB.onestopsolution.MAIN.Task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import nitmas.groupB.onestopsolution.R
import kotlin.collections.ArrayList

class channelAdapter( val channellist:ArrayList<channelDB>, private val handleclicki:handleclicki):RecyclerView.Adapter<channelAdapter.mychannelAdapter>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mychannelAdapter {
        val itemView=LayoutInflater.from(parent.context)
            .inflate(R.layout.item_channel,parent,false)
        val view =mychannelAdapter(itemView)
        itemView.setOnClickListener {
           handleclicki.onItemClicked(channellist[view.adapterPosition])
        }
        return view
    }

    override fun onBindViewHolder(holder: mychannelAdapter, position: Int) {

        val current=channellist[position]


        holder.channelname.text=current.channelName
        holder.steacher.text=current.TeachersName
        holder.currsem.text=current.currentSem





    }

    override fun getItemCount(): Int {
        return channellist.size
    }

    class mychannelAdapter(itemView: View):RecyclerView.ViewHolder(itemView){

        val channelname:TextView=itemView.findViewById(R.id.channelname)
        val steacher:TextView=itemView.findViewById(R.id.subjectTeacherName)
        val currsem:TextView=itemView.findViewById(R.id.currentSem)



    }

}
interface handleclicki{
    fun onItemClicked(item: channelDB)
}