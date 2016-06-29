package controller;

import controller.dto.ResponsePackDto;
import dao.condition.AccidentCondition;
import model.Accident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.AccidentService;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by wenqing on 2016/6/29.
 */
@Controller
@RequestMapping(value = "/accident")
public class AccidentController extends BaseController {

    @Autowired
    private AccidentService accidentService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto add(@RequestBody Accident Accident) {
        ResponsePackDto dto = new ResponsePackDto();
        if(accidentService.add(Accident))
            return dto;
        else {
            dto.setStatus(500);
            dto.setError("��������ʧ��");
        }
        return dto;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto delete(Integer id) {
        ResponsePackDto dto = new ResponsePackDto();
        List<Integer> ids = new LinkedList<Integer>();
        ids.add(id);
        if(accidentService.delete(ids)) {
            return dto;
        }
        else {
            dto.setStatus(500);
            dto.setError("ɾ������ʧ��");
        }
        return dto;
    }

    @RequestMapping(value = "/deletes", method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto delete(List<Integer> ids) {
        ResponsePackDto dto = new ResponsePackDto();
        if(accidentService.delete(ids)) {
            return dto;
        }
        else {
            dto.setStatus(500);
            dto.setError("ɾ������ʧ��");
        }
        return dto;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto edit(@RequestBody Accident newAccident) {
        ResponsePackDto dto = new ResponsePackDto();
        if(accidentService.edit(newAccident)) {
            return dto;
        }
        else {
            dto.setStatus(500);
            dto.setError("�޸�����ʧ��");
        }
        return dto;
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto query(@RequestBody AccidentCondition condition) {
        List<Accident> Accidents = accidentService.query(condition);
        return new ResponsePackDto(Accidents);
    }

}