package com.metehanbolat.thinqeek.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.metehanbolat.thinqeek.R
import com.metehanbolat.thinqeek.databinding.SeriesRecyclerRowBinding
import com.metehanbolat.thinqeek.model.Series
import com.metehanbolat.thinqeek.view.fragments.SeriesFragmentDirections
import com.metehanbolat.thinqeek.viewmodel.SeriesFragmentViewModel
import com.squareup.picasso.Picasso

class SeriesRecyclerAdapter(var context : Context, var seriesList : ArrayList<Series>, var viewModel : SeriesFragmentViewModel) : RecyclerView.Adapter<SeriesRecyclerAdapter.SeriesViewHolder>() {
    class SeriesViewHolder(val binding : SeriesRecyclerRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val binding = SeriesRecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SeriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        holder.binding.seriesName.text = seriesList[position].name
        holder.binding.seriesYear.text = seriesList[position].year.toString()
        holder.binding.seriesRate.text = seriesList[position].rate.toString()
        holder.binding.seriesSeason.text = context.resources.getString(R.string.recycler_season, seriesList[position].season)
        Picasso.get().load(seriesList[position].downloadUrl).into(holder.binding.seriesImage)

        holder.binding.seriesRecyclerConstraint.setOnClickListener {
            if (viewModel.isClickable.value == true){
                val action = SeriesFragmentDirections.actionSeriesFragmentToContentDetailsFragment(
                    director = seriesList[position].director,
                    name = seriesList[position].name,
                    comment = seriesList[position].comment,
                    downloadUrl = seriesList[position].downloadUrl,
                    rate = seriesList[position].rate.toString(),
                    season = seriesList[position].season.toString(),
                    year = seriesList[position].year.toString(),
                    time = seriesList[position].time,
                    stars = seriesList[position].stars,
                    author = seriesList[position].author,
                    subject = seriesList[position].subject,
                    isWhat = "Series",
                    type = seriesList[position].type
                )
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    override fun getItemCount(): Int {
        return seriesList.size
    }
}