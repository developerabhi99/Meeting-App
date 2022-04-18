package nitmas.groupB.onestopsolution.MAIN.Task


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import nitmas.groupB.onestopsolution.R

class testplaceadapter(val testplacelist:ArrayList<testplaceDB>, private val handleclic1: handleclic1):RecyclerView.Adapter<testplaceadapter.mytestplaceadapter>() {
    class mytestplaceadapter(itemView: View):RecyclerView.ViewHolder(itemView){
        val question:TextView =itemView.findViewById(R.id.testquestion)
        val option1:Button=itemView.findViewById(R.id.option1)
        val option2:Button=itemView.findViewById(R.id.option1)
        val option3:Button=itemView.findViewById(R.id.option1)
        val option4:Button=itemView.findViewById(R.id.option1)


    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): mytestplaceadapter {
        val itemView= LayoutInflater.from(parent.context)
            .inflate(R.layout.item_onlinetest,parent,false)
        val view = testplaceadapter.mytestplaceadapter(itemView)
        itemView.setOnClickListener {
            handleclic1.onItemClicke1(testplacelist[view.adapterPosition])
        }
        return view
    }


    override fun onBindViewHolder(holder: mytestplaceadapter, position: Int) {

        val current=testplacelist[position]

        holder.question.text=current.Question
        holder.option1.text=current.option1
        holder.option2.text=current.option2
        holder.option3.text=current.option3
        holder.option4.text=current.option4

       




    }

    override fun getItemCount(): Int {
       return testplacelist.size
    }
}
interface handleclic1{
    fun onItemClicke1(item: testplaceDB)
}