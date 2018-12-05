package com.example.aaron.popularmoviespractice.ui.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aaron.popularmoviespractice.R
import com.example.aaron.popularmoviespractice.data.Movie
import com.example.aaron.popularmoviespractice.databinding.DetailFragmentBinding
import com.example.aaron.popularmoviespractice.viewModel.DetailViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.detail_fragment.*


class DetailFragment : Fragment() {
    var movie: Movie? = null
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return inflater.inflate(R.layout.detail_fragment, container, false)
        val binding = DataBindingUtil.inflate<DetailFragmentBinding>(inflater,R.layout.detail_fragment, container, false)
        //here data must be an instance of the class MarsDataProvider
       //binding.(data)
        val view = binding.root
        viewModel = DetailViewModel()
        //ADD VIEWMODEL
        binding.dataViewModel = viewModel
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)

        movie?.let {
            viewModel.movieTitle = it.title
            viewModel.movieYear = it.date
            viewModel.movieDescription = it.synopsis
            //viewModel.movieLength =
            viewModel.movieRate = it.rating
            val picasso = Picasso.get()
            picasso.isLoggingEnabled = true
            picasso.load(it.image)
                .error(android.R.drawable.stat_notify_error)
                .fit()
                .into(det_imageMovie)
        }
    }

}
