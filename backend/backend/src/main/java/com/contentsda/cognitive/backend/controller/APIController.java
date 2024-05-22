package com.contentsda.cognitive.backend.controller;

import com.contentsda.cognitive.backend.entity.*;
import com.contentsda.cognitive.backend.repository.*;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class APIController {
    @Autowired
    private TestResultRepository testResultRepository;
    @Autowired
    private ATestResultRepository aTestResultRepository;
    @Autowired
    private MartMemoryTestResultRepository martMemoryTestResultRepository;
    @Autowired
    private SeparateVisuospatialTestResultRepository separateVisuospatialTestResultRepository;
    @Autowired
    private MealAttentionTestResultRepository mealAttentionTestResultRepository;
    @Autowired
    private MicrowaveExecuteTestResultRepository microwaveExecuteTestResultRepository;

    @Autowired
    private BTestResultRepository bTestResultRepository;
    @Autowired
    private PaintMemoryTestResultRepository paintMemoryTestResultRepository;
    @Autowired
    private DeskVisuospatialTestResultRepository deskVisuospatialTestResultRepository;
    @Autowired
    private CallAttentionTestResultRepository callAttentionTestResultRepository;
    @Autowired
    private WashingMachineExecuteTestResultRepository washingMachineExecuteTestResultRepository;

    @Autowired
    private ExecuteLogRepository executeLogRepository;

    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private SupervisionRepository supervisionRepository;
    @Autowired
    private TestSubjectRepository testSubjectRepository;
    @Autowired
    private DeviceRepository deviceRepository;

    private final AuthenticationManager authenticationManager;
    private static final Logger logger = LoggerFactory.getLogger(APIController.class);

    @Autowired
    public APIController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    @PostMapping("/login-test")
    public ResponseEntity<String> loginTest(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        logger.info("Login endpoint hit");
        try {
            String loginId = loginRequest.getLoginId();
            String loginPw = loginRequest.getLoginPw();

            logger.info("Received login attempt with username: {}", loginId);
            logger.info("Received login attempt with password: {}", loginPw);

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginId, loginPw)
            );
            if (authentication.isAuthenticated()) {
                logger.info("Authentication successful for username: {}", loginId);
                return ResponseEntity.ok("Login successful");
            } else {
                logger.warn("Authentication failed for username: {}", loginId);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
            }
        } catch (AuthenticationException e) {
            logger.error("Authentication exception for username: {}", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Exception Login failed: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response){
        String loginId = loginRequest.getLoginId();
        String loginPw = loginRequest.getLoginPw();

        Supervision supervision = supervisionRepository.findByLoginId(loginId);
        if (supervision == null && supervision.getLoginPw() == passwordEncoder.encode(loginPw)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(loginPw);
    }

    @PostMapping("/a-test-result")
    public String addATestResult(@RequestBody TestResultData testResultData){
        MartMemoryTestResult martMemoryTestResult = new MartMemoryTestResult();
        martMemoryTestResult.setMartMemoryResult1(testResultData.isMartMemoryResult1());
        martMemoryTestResult.setMartMemoryResult2(testResultData.isMartMemoryResult2());
        martMemoryTestResult.setMartMemoryResult3(testResultData.isMartMemoryResult3());
        martMemoryTestResult.setCorrectItem1(testResultData.getCorrectItem1());
        martMemoryTestResult.setCorrectItem2(testResultData.getCorrectItem2());
        martMemoryTestResult.setCorrectItem3(testResultData.getCorrectItem3());
        martMemoryTestResult.setMartRememberResult(testResultData.isMartRememberResult());
        martMemoryTestResult.setRememberCorrectItem(testResultData.getRememberCorrectItem());

        martMemoryTestResultRepository.save(martMemoryTestResult);

        SeparateVisuospatialTestResult separateVisuospatialTestResult = new SeparateVisuospatialTestResult();
        separateVisuospatialTestResult.setSeparateResult(testResultData.isSeperateResult());
        separateVisuospatialTestResult.setSeparateCount(testResultData.getSeperateCount());
        separateVisuospatialTestResult.setSeparatePlasticCount(testResultData.getPlasticCount());
        separateVisuospatialTestResult.setSeparateCanCount(testResultData.getCanCount());
        separateVisuospatialTestResult.setSeparateGlassCount(testResultData.getGlassCount());
        separateVisuospatialTestResult.setSeparateVinylCount(testResultData.getVinylCount());
        separateVisuospatialTestResult.setSeparateTime(testResultData.getSeperateTime());

        separateVisuospatialTestResultRepository.save(separateVisuospatialTestResult);

        MealAttentionTestResult mealAttentionTestResult = new MealAttentionTestResult();
        mealAttentionTestResult.setMealResult(testResultData.isMealResult());
        mealAttentionTestResult.setMealCount(testResultData.getMealCount());

        mealAttentionTestResultRepository.save(mealAttentionTestResult);

        MicrowaveExecuteTestResult microwaveExecuteTestResult = new MicrowaveExecuteTestResult();
        microwaveExecuteTestResult.setMicrowaveResult(testResultData.isMicrowaveResult());
        microwaveExecuteTestResult.setMicrowaveTime(testResultData.getMicrowaveTime());

        microwaveExecuteTestResultRepository.save(microwaveExecuteTestResult);

        int logNum = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        for(int i = 0; i < testResultData.getMicrowaveLog().size(); i++){
            ExecuteLog executeLog = new ExecuteLog();
            executeLog.setLogNum(Long.valueOf(logNum));
            logNum++;
            executeLog.setLog(testResultData.getMicrowaveLog().get(i));
            executeLog.setLogTime(LocalDateTime.parse(testResultData.getMicrowaveLogTime().get(i), formatter));
            executeLog.setMicrowaveExecuteTestResult(microwaveExecuteTestResult);
            executeLogRepository.save(executeLog);
        }

        ATestResult aTestResult = new ATestResult();
        aTestResult.setMartMemoryTestResult(martMemoryTestResult);
        aTestResult.setSeparateVisuospatialTestResult(separateVisuospatialTestResult);
        aTestResult.setMealAttentionTestResult(mealAttentionTestResult);
        aTestResult.setMicrowaveExecuteTestResult(microwaveExecuteTestResult);
        aTestResultRepository.save(aTestResult);


        return "ATestResult saved successfully";
    }

    @PostMapping("/b-test-result")
    public String addBTestResult(@RequestBody TestResultData testResultData){
        PaintMemoryTestResult paintMemoryTestResult = new PaintMemoryTestResult();
        paintMemoryTestResult.setPaintMemoryResult1(testResultData.isPaintMemoryResult1());
        paintMemoryTestResult.setPaintMemoryResult2(testResultData.isPaintMemoryResult2());
        paintMemoryTestResult.setPaintMemoryResult3(testResultData.isPaintMemoryResult3());
        paintMemoryTestResult.setCorrectPaint1(testResultData.getCorrectPaint1());
        paintMemoryTestResult.setCorrectPaint2(testResultData.getCorrectPaint2());
        paintMemoryTestResult.setCorrectPaint3(testResultData.getCorrectPaint3());
        paintMemoryTestResult.setPaintRememberResult(testResultData.isPaintRememberResult());
        paintMemoryTestResult.setRememberCorrectPaint(testResultData.getRememberCorrectPaint());

        paintMemoryTestResultRepository.save(paintMemoryTestResult);

        DeskVisuospatialTestResult deskVisuospatialTestResult = new DeskVisuospatialTestResult();
        deskVisuospatialTestResult.setDeskResult(testResultData.isDeskResult());
        deskVisuospatialTestResult.setDeskCount(testResultData.getDeskCount());
        deskVisuospatialTestResult.setDeskTime(testResultData.getDeskTime());
        deskVisuospatialTestResult.setDeskBookResult(testResultData.isDeskBookResult());
        deskVisuospatialTestResult.setDeskBookCount(testResultData.getDeskBookCount());
        deskVisuospatialTestResult.setDeskWritingResult(testResultData.isDeskWritingResult());
        deskVisuospatialTestResult.setDeskWritingCount(testResultData.getDeskWritingCount());

        deskVisuospatialTestResultRepository.save(deskVisuospatialTestResult);

        CallAttentionTestResult callAttentionTestResult = new CallAttentionTestResult();
        callAttentionTestResult.setCallResult(testResultData.isCallResult());
        callAttentionTestResult.setCallCount(testResultData.getCallCount());

        callAttentionTestResultRepository.save(callAttentionTestResult);

        WashingMachineExecuteTestResult washingMachineExecuteTestResult = new WashingMachineExecuteTestResult();
        washingMachineExecuteTestResult.setWashingMachineResult(testResultData.isWashingMachineResult());
        washingMachineExecuteTestResult.setWashingMachineTime(testResultData.getWashingMachineTime());

        washingMachineExecuteTestResultRepository.save(washingMachineExecuteTestResult);

        int logNum = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        for(int i = 0; i < testResultData.getWashingMachineLog().size(); i++){
            ExecuteLog executeLog = new ExecuteLog();
            executeLog.setLogNum(Long.valueOf(logNum));
            logNum++;
            executeLog.setLog(testResultData.getWashingMachineLog().get(i));
            executeLog.setLogTime(LocalDateTime.parse(testResultData.getWashingMachineLogTime().get(i), formatter));
            executeLog.setWashingMachineExecuteTestResult(washingMachineExecuteTestResult);
            executeLogRepository.save(executeLog);
        }

        BTestResult bTestResult = new BTestResult();
        bTestResult.setPaintMemoryTestResult(paintMemoryTestResult);
        bTestResult.setDeskVisuospatialTestResult(deskVisuospatialTestResult);
        bTestResult.setCallAttentionTestResult(callAttentionTestResult);
        bTestResult.setWashingMachineExecuteTestResult(washingMachineExecuteTestResult);
        bTestResultRepository.save(bTestResult);

        return "BTestResult saved successfully";
    }

    @GetMapping("/patient-info")
    public String patientInfo(){


        return "patientInfo sent successfully";
    }

    @GetMapping("/get-test")
    public TestResultData getTest() {
        TestResultData testResultData = new TestResultData();
        testResultData.setTestType("A");
        return testResultData;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("test-insert")
    public String testInsert(){
        Organization organization = new Organization();
        organization.setName("testOrganization");
        organization.setDescription("test description");
        organization.setCreatedDate(LocalDateTime.now());
        organizationRepository.save(organization);

        Supervision supervision = new Supervision();
        supervision.setSupervisionName("testSupervision");
        supervision.setLoginId("test@test.com");
        supervision.setLoginPw(passwordEncoder.encode("1234"));
        supervision.setCreatedDate((LocalDateTime.now()));
        supervision.setOrganization(organization);
        supervisionRepository.save(supervision);

        Device device = new Device();
        device.setDeviceName("testDevice");
        device.setDeviceNum(1);
        device.setOrganization(organization);
        deviceRepository.save(device);

        TestSubject testSubject = new TestSubject();
        testSubject.setName("testSubject");
        testSubject.setAge(60);
        testSubject.setGender("male");
        testSubject.setCreatedDate(LocalDateTime.now());
        testSubject.setSupervision(supervision);
        testSubjectRepository.save(testSubject);

        return "good";
    }
}
