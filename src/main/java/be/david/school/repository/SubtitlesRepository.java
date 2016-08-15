package be.david.school.repository;

import be.david.school.model.Flm_ratings;
import be.david.school.model.Subtitles;

import java.util.List;

/**
 * Created by David on 7/08/2016.
 */
public interface SubtitlesRepository {

    List<Subtitles> findAllSubtitles();
}
