package com.metehanbolat.thinqeek.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.metehanbolat.thinqeek.databinding.MoviesRecyclerRowBinding
import com.metehanbolat.thinqeek.model.Movies
import com.metehanbolat.thinqeek.view.fragments.MoviesFragmentDirections
import com.metehanbolat.thinqeek.viewmodel.MoviesFragmentViewModel
import com.squareup.picasso.Picasso

class MoviesRecyclerAdapter(var context: Context, var movieList: ArrayList<Movies>, var viewModel: MoviesFragmentViewModel) :  RecyclerView.Adapter<MoviesRecyclerAdapter.MoviesViewHolder>() {
    class MoviesViewHolder(val binding : MoviesRecyclerRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding = MoviesRecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.binding.movieName.text = movieList[position].name
        holder.binding.movieYear.text = movieList[position].year.toString()
        holder.binding.movieRate.text = movieList[position].rate.toString()
        Picasso.get().load(movieList[position].downloadUrl).into(holder.binding.movieImage)

        holder.binding.movieRecyclerConstraint.setOnClickListener {
            if (viewModel.isClickable.value == true){
                val action = MoviesFragmentDirections.actionMoviesFragmentToContentDetailsFragment(
                    isWhat = "Movies",
                    director = movieList[position].director,
                    name = movieList[position].name,
                    comment = movieList[position].comment,
                    downloadUrl = movieList[position].downloadUrl,
                    rate = movieList[position].rate.toString(),
                    year = movieList[position].year.toString(),
                    season = "",
                    time = movieList[position].time,
                    stars = movieList[position].stars,
                    author = movieList[position].author,
                    subject = movieList[position].subject,
                    type = movieList[position].type
                )
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

}