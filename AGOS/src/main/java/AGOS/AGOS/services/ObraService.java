package AGOS.AGOS.services;

import AGOS.AGOS.entity.*;
import AGOS.AGOS.repository.ObraRepository;
import AGOS.AGOS.repository.PeriodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class ObraService {


    @Autowired
    private PeriodoRepository periodoRepository;
    @Autowired
    private ObraRepository obraRepository;



}
