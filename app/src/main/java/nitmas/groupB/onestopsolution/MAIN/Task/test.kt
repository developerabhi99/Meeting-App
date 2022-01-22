package nitmas.groupB.onestopsolution.MAIN.Task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import nitmas.groupB.onestopsolution.R


class test : Fragment(), handleclickio {

    lateinit var recyclerView: RecyclerView
    lateinit var testlist:ArrayList<testDB>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView= inflater.inflate(R.layout.fragment_test, container, false)

        recyclerView=rootView.findViewById(R.id.testrv)

        recyclerView.layoutManager=LinearLayoutManager(activity)

        recyclerView.setHasFixedSize(true)

        testlist= arrayListOf<testDB>()

        testlist.add(testDB("DBMS EXAM","DSD SIR ","6th SEM"))
        testlist.add(testDB("COMPUTER NETWORK EXAM","SNM SIR","6th SEM"))

        testlist.add(testDB("JAVA EXAM","ABHIJIT SIR ","6th SEM"))
        testlist.add(testDB("JAVA EXAM","ABHIJIT SIR ","6th SEM"))
        testlist.add(testDB("JAVA EXAM","ABHIJIT SIR ","6th SEM"))
        testlist.add(testDB("JAVA EXAM","ABHIJIT SIR ","6th SEM"))
        testlist.add(testDB("JAVA EXAM","ABHIJIT SIR ","6th SEM"))


      recyclerView.adapter=testAdapter(testlist,this)


        return rootView
    }

    override fun onItemClickedd(item: testDB) {
        Toast.makeText(context," iteclicked", Toast.LENGTH_SHORT).show()
    }

}
