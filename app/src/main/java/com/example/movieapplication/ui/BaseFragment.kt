package com.example.movieapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment<T: ViewBinding>: DaggerFragment() {
    protected abstract fun getContentResource(): Int

    protected var binding: T? = null

    private lateinit var compositeDisposable: CompositeDisposable

    protected abstract fun setupViewBinding(view: View)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        compositeDisposable = CompositeDisposable()
        return inflater.inflate(getContentResource(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewBinding(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}