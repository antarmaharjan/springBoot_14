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
    MovieRepository movieRepository;
    @RequestMapping("/")
    public String index(Model model){
        //create a director
        Director director = new Director();
        director.setName("Stephen Bullock");
        director.setGenre("Sci Fi");

        //Now let's create a movies
        Movie movie= new Movie();
        movie.setTitle("star movies");
        movie.setYear(2017);
        movie.setDescription("About Starts...");

        //Add the movies to an empty list
        Set<Movie> movies = new HashSet<Movie>();
        movies.add(movie);

        movie=new Movie();
        movie.setTitle("DeathStar Ewoks");
        movie.setYear(2011);
        movie.setDescription("About Ewoks on the DeathStar...");
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
    public String processMovie(@Valid Movie movie, BindingResult result){
        if(result.hasErrors()){
            return "movieform";
        }
        movieRepository.save(movie);
        return "index1";


//        System.out.pirintln(directorName);
//        System.out.println(movie.getTitle());

//        Director director1 = directorRepository.findFirstByNameContains("directorName");
//        System.out.println(director1.getId());
//        Set<Movie> allmov = director1.getMovies();
//        allmov.add(movie);
//        director1.setMovies(allmov);
//
//        model.addAttribute("Director1", director1);
//        model.addAttribute("movie", movie);
//        return "movieconfirm";
    }
    @GetMapping("/adddirector")
    public String adddirector(Model model){
        model.addAttribute("director",new Director());
       // model.addAttribute("movielist",movieRepository.findAll());
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
    @RequestMapping("/showMovies")
    public String showMovies(Model model){
        model.addAttribute("listMovies",movieRepository.findAll());
        return "showMovies";
    }

    @GetMapping("/direcotrlist")
    public String directorlist(Model model){
        Iterable<Director>alldirector =directorRepository.findAll();
        model.addAttribute("alldirector",alldirector);
        return "directorlist";
    }
}
