package com.frankmoley.lil.learningspring.Web;

import com.frankmoley.lil.learningspring.business.domain.RoomReservation;
import com.frankmoley.lil.learningspring.business.service.ReservationService;
import com.frankmoley.lil.learningspring.data.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/reservations") //define that when this address is referenced off the route application it references this controller
public class RoomReservationWebController {
    private  final ReservationService reservationService;

    @Autowired
    public RoomReservationWebController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    @GetMapping //at the RequestMapping url any GET request will be responded to by this method
    public String getReservations(@RequestParam(value="date", required=false)String dateString, Model model){
    Date date =DateUtils.createDateFromDateString(dateString);
        List<RoomReservation> roomReservations= this.reservationService.getRoomReservationsForDate(date);
        model.addAttribute("roomReservations", roomReservations);
        return "reservations";

   }
}
