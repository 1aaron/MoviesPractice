package com.example.aaron.popularmoviespractice.ui.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aaron.popularmoviespractice.R
import com.example.aaron.popularmoviespractice.adapters.MoviesAdapter
import com.example.aaron.popularmoviespractice.viewModel.MainViewModel
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {
    lateinit var gridLayoutManager: GridLayoutManager
    var adapter: MoviesAdapter? = null

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        por_layoutMovies.setHasFixedSize(true)
        gridLayoutManager = GridLayoutManager(context,2)
        //todo: check for better approach
        /*if(Statics.screenWidth > 720 && !Statics.land){
            gridLayoutManager = GridLayoutManager(context,3)

        }else{
            gridLayoutManager = GridLayoutManager(context,2)
        }*/
        //prepare recycler view
        por_layoutMovies.layoutManager = gridLayoutManager
        context?.let {
            context ->
            adapter = MoviesAdapter(context)
            por_layoutMovies.adapter = adapter
        }

        viewModel.getMovies().observe(this, Observer {
            movies ->
            adapter?.let {
                adapter ->
                adapter.setData(movies!!)
            }
        })

        //por_layoutMovies.adapter = adapter
    }

}
