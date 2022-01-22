package nitmas.groupB.onestopsolution.MAIN.Task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import nitmas.groupB.onestopsolution.R

class testAdapter(val testlist:ArrayList<testDB>, private val handleclickio: handleclickio):RecyclerView.Adapter<testAdapter.mytestAdapter>() {



    class mytestAdapter(itemView: View):RecyclerView.ViewHolder(itemView){

        val subjecttest: TextView =itemView.findViewById(R.id.Tsubjectname)
        val Tteacher: TextView =itemView.findViewById(R.id.TsubjectTeacherName)
        val Tcurrsem: TextView =itemView.findViewById(R.id.TcurrentSem)


    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): mytestAdapter {
        val itemView= LayoutInflater.from(parent.context)
            .inflate(R.layout.item_test,parent,false)
       val view = testAdapter.mytestAdapter(itemView)
       itemView.setOnClickListener {
           handleclickio.onItemClickedd(testlist[view.adapterPosition])
      }
        return view
    }

    override fun onBindViewHolder(holder: mytestAdapter, position: Int) {

        val current=testlist[position]

        holder.subjecttest.text=current.SubjectName
        holder.Tteacher.text=current.TeachersName
        holder.Tcurrsem.text=current.currentSem

    }

    override fun getItemCount(): Int {
        return testlist.size
    }
}

interface handleclickio{
    fun onItemClickedd(item: testDB)
}