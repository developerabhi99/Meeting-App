package nitmas.groupB.onestopsolution.MAIN.Task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import nitmas.groupB.onestopsolution.R



class Testplace : Fragment(),handleclic1 {
    lateinit var testplacelist:ArrayList<testplaceDB>
    lateinit var testplaceview: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootview=inflater.inflate(R.layout.fragment_testplace, container, false)



        testplaceview=rootview.findViewById(R.id.onlinetestrv)

        testplaceview.layoutManager= LinearLayoutManager(activity)

        testplaceview.setHasFixedSize(true)

        testplacelist= arrayListOf<testplaceDB>()

        testplacelist.add(testplaceDB("1","what is DBMS ?","Donot know","will not tell","do your own bussiness","DataBase Management System","DataBase Management System","ch98"))
        testplacelist.add(testplaceDB("1","what is DBMS ?","Donot know","will not tell","do your own bussiness","DataBase Management System","DataBase Management System","ch98"))
        testplacelist.add(testplaceDB("1","what is DBMS ?","Donot know","will not tell","do your own bussiness","DataBase Management System","DataBase Management System","ch98"))


        testplaceview.adapter=testplaceadapter(testplacelist,this)




        return rootview
    }

    override fun onItemClicke1(item: testplaceDB) {

    }

}