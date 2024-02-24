package com.example.criminalintent

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.criminalintent.databinding.ListItemCrimeBinding
import com.example.criminalintent.databinding.ListItemSeriousCrimeBinding
import java.lang.IllegalArgumentException

// a subclass of RecyclerView.ViewHolder()
// a ViewHolder
class CrimeHolder(var binding: ListItemCrimeBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()
        binding.root.setOnClickListener { view: View ->
            Toast.makeText(binding.root.context, "${crime.title} clicked!", Toast.LENGTH_SHORT).show()
        }
    }
}

// a subclass of RecyclerView.ViewHolder()
// a ViewHolder
class SeriousCrimeHolder(var binding: ListItemSeriousCrimeBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()
        binding.root.setOnClickListener { view: View ->
            Toast.makeText(binding.root.context, "${crime.title} clicked!", Toast.LENGTH_SHORT).show()
        }
        binding.crimeContactPoliceButton.setOnClickListener { view: View ->
            Toast.makeText(binding.root.context, "Call the police!", Toast.LENGTH_SHORT).show()
        }
    }
}

// an Adapter
private const val CRIME_VIEW_TYPE = 0
private const val SERIOUS_CRIME_VIEW_TYPE = 1
class CrimeListAdapter(private val crimes: List<Crime>) :RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    // after the RecyclerView created a View, gives it a ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
        // get the ListItemCrimeBinding here and create a ViewHolder object
        return when (viewType) {
            CRIME_VIEW_TYPE -> CrimeHolder(ListItemCrimeBinding.inflate(inflater, parent, false))
            SERIOUS_CRIME_VIEW_TYPE -> SeriousCrimeHolder(ListItemSeriousCrimeBinding.inflate(inflater, parent, false))
            else -> throw IllegalArgumentException("unknown type of holder: ViewHolder!")
        }
    }


    // the RecyclerView ask for data of the many ViewHolder (with index=position)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val crime = crimes[position]
//        holder.apply {
//            binding.crimeTitle.text = crime.title
//            binding.crimeDate.text = crime.date.toString()
//        }
        when (holder) {
            is CrimeHolder -> holder.bind(crimes[position])
            is SeriousCrimeHolder -> holder.bind(crimes[position])
            else -> throw IllegalArgumentException("unknown type of holder: ViewHolder!")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (!crimes[position].requiresPolice) CRIME_VIEW_TYPE else SERIOUS_CRIME_VIEW_TYPE
        // 0: list_item_crime.xml
        // 1: list_item_crime_serious.xml
    }

    // let the RecyclerView know how many items are there to render
    override fun getItemCount(): Int {
        return crimes.size
    }

}