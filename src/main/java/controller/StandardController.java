package controller;

import controller.dto.ResponsePackDto;
import dao.condition.StandardCondition;
import model.Standard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.StandardService;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by wenqing on 2016/6/29.
 */
@Controller
@RequestMapping(value = "/standard")
public class StandardController extends BaseController {

    @Autowired
    private StandardService standardService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto add(@RequestBody Standard standard) {
        ResponsePackDto dto = new ResponsePackDto();
        if(standardService.add(standard))
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
        if(standardService.delete(ids)) {
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
        if(standardService.delete(ids)) {
            return dto;
        }
        else {
            dto.setStatus(500);
            dto.setError("ɾ������ʧ��");
        }
        return dto;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto edit(@RequestBody Standard newStandard) {
        ResponsePackDto dto = new ResponsePackDto();
        if(standardService.edit(newStandard)) {
            return dto;
        }
        else {
            dto.setStatus(500);
            dto.setError("�޸�����ʧ��");
        }
        return dto;
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto query(@RequestBody StandardCondition condition) {
        List<Standard> standards = standardService.query(condition);
        return new ResponsePackDto(standards);
    }

}