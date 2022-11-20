package com.example.mylovefilms;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieDetailViewModel extends AndroidViewModel {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private static final String TAG = "MovieDetailViewModel";
    private final MutableLiveData<List<Review>> reviews = new MutableLiveData<>();
    private final MutableLiveData<List<Trailers>> trailers = new MutableLiveData<>();
    private final MovieDao movieDao;

    public MovieDetailViewModel(@NonNull Application application) {
        super(application);
        movieDao = MovieDatabBase.getInstance(application).movieDao();

    }

    public LiveData<Movie> getFavFilm(int movieId){
        return movieDao.getFavMovie(movieId);
    }

    public LiveData<List<Trailers>> getTrailers() {
        return trailers;
    }

    public LiveData<List<Review>> getReviews() {
        return reviews;
    }

    public void loadReviews(int id){
        Disposable disposable = ApiFactory.apiservice.loadReview(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<ReviewRespons, List<Review>>() {
                    @Override
                    public List<Review> apply(ReviewRespons reviewRespons) throws Throwable {
                        return reviewRespons.getReviewList().getReviewList();
                    }
                })
                .subscribe(reviews::setValue, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d(TAG, throwable.toString());
                    }
                });
        compositeDisposable.add(disposable);
    }

    public void insertMovie(Movie movie){
        Disposable disposable = movieDao.insertMovie(movie)
                .observeOn(Schedulers.io())
                .subscribe();
        compositeDisposable.add(disposable);
    }

    public void deleteMovie(int movieId){
        Disposable disposable = movieDao.deleteMovie(movieId)
                .observeOn(Schedulers.io())
                .subscribe();
        compositeDisposable.add(disposable);
    }

    public void loadTrailers(int id){
        Disposable disposable = ApiFactory.apiservice.loadTrailers(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<TrailersRespons, List<Trailers>>() {
                    @Override
                    public List<Trailers> apply(TrailersRespons trailersRespons) throws Throwable {
                        return trailersRespons.getTrailersLIst().getListTrailers();
                    }
                })
                .subscribe(trailers::setValue, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d(TAG, throwable.toString());

                    }
                });
        compositeDisposable.add(disposable);
    }
}
