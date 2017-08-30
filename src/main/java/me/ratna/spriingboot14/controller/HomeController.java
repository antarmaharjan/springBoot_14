package me.ratna.spriingboot14.controller;

import me.ratna.spriingboot14.models.Director;
import me.ratna.spriingboot14.models.Movie;
import me.ratna.spriingboot14.repositories.DirectorRepository;
import me.ratna.spriingboot14.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {
    @Autowired
    DirectorRepository directorRepository;
    @Autowired
    MovieRepository movieRepository;
    @RequestMapping("/")
    public String index(Model model){
        //First let's create a director
        Director director = new Director();
        director.setName("Stephen Bullock");
        director.setGenre("Sci Fi");

        //Now let's create a movies
        Movie movie= new Movie();
        movie.setTitle("star movies");
        movie.setYear(2017);
        movie.setDescription("About Starts...");
        //movie.setDirector(director);

        //Add the movies to an empty list
        Set<Movie> movies = new HashSet<Movie>();
        movies.add(movie);

        movie=new Movie();
        movie.setTitle("DeathStar Ewoks");
        movie.setYear(2011);
        movie.setDescription("About Ewoks on the DeathStar...");
        movie.setDirector(director);
        movies.add(movie);

        //Add the list of movies to the director's movie list
        director.setMovies(movies);

        //save the director to the database
        directorRepository.save(director);

        //grad all the directors from the database and send them to the template
        model.addAttribute("directors", directorRepository.findAll());
        return "index1";
    }
    @GetMapping("/addmovie")
    public String addmovie(Model model){
        model.addAttribute("movie",new Movie());
        return "movieform";
    }
    @PostMapping("/addmovie")
    public String processMovie(@Valid @ModelAttribute("movie") Movie movie, BindingResult result){
        if(result.hasErrors()){
            return "movieform";
        }
        movieRepository.save(movie);
        return "index1";
    }
    @GetMapping("/adddirector")
    public String adddirector(Model model){
        model.addAttribute("director",new Director());
        //model.addAttribute("movielist",movieRepository.findAll());
        return "directorform";
    }
    @PostMapping("/adddirector")
    public String processDirector(@Valid Director director,BindingResult result){
        if(result.hasErrors()){
            return "directorform";
        }
        directorRepository.save(director);
        return "index1";
    }
    @RequestMapping("/showDirectors")
    public String showDirectors(Model model){
        model.addAttribute("Directorslist",directorRepository.findAll());
        return "showDirectors";
    }

    @GetMapping("/movielist")
    public String showMovies(Model model){
        Iterable<Movie>allmovies =movieRepository.findAll();
        model.addAttribute("allmovies",allmovies);
        return "movielist";
    }
    @GetMapping("/movielist/{id}")
    public String showmovlist(@PathVariable("id") long id,Model model){
        model.addAttribute("movie",movieRepository.findOne(id));
        return "movie";
    }

    @GetMapping("/direcotrlist")
    public String directorlist(Model model){
        Iterable<Director>alldirector =directorRepository.findAll();
        model.addAttribute("alldirector",alldirector);
        return "directorlist";
    }
    @GetMapping("/directorlist/{id}")
    public String showMovies(@PathVariable("id") long id,Model model){
        model.addAttribute("director",directorRepository.findOne(id));
        return "director";
    }
}
