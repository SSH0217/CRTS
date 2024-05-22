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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
    private CTestResultRepository cTestResultRepository;
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
    @Autowired
    private ContentsRepository contentsRepository;

    private final AuthenticationManager authenticationManager;
    private static final Logger logger = LoggerFactory.getLogger(APIController.class);

    @Autowired
    public APIController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    @PostMapping("/login")
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

    @PostMapping("/login-real")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){
        String loginId = loginRequest.getLoginId();
        String loginPw = loginRequest.getLoginPw();

        Supervision supervision = supervisionRepository.findByLoginId(loginId);
        if (supervision == null || !passwordEncoder.matches(loginPw, supervision.getLoginPw())) {
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

        TestResult testResult = new TestResult();
        testResult.setATestResult(aTestResult);
        testResult.setTestSubject(testSubjectRepository.findById(Long.valueOf(testResultData.getPatientID())).orElse(null));
        testResult.setTestStartTime(LocalDateTime.parse(testResultData.getStartTime(), formatter));
        testResult.setTestEndTime(LocalDateTime.parse(testResultData.getEndTime(), formatter));
        testResultRepository.save(testResult);

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

        TestResult testResult = new TestResult();
        testResult.setBTestResult(bTestResult);
        testResult.setTestSubject(testSubjectRepository.findById(Long.valueOf(testResultData.getPatientID())).orElse(null));
        testResult.setTestStartTime(LocalDateTime.parse(testResultData.getStartTime(), formatter));
        testResult.setTestEndTime(LocalDateTime.parse(testResultData.getEndTime(), formatter));
        testResultRepository.save(testResult);

        return "BTestResult saved successfully";
    }

    @PostMapping("/c-test-result")
    public String addCTestResult(@RequestBody CTestData cTestData){
        CTestResult cTestResult = new CTestResult();
        cTestResult.setAttentionScore(cTestData.getAttentionScore());
        cTestResult.setMemoryScore(cTestData.getMemoryScore());
        cTestResult.setVisuospatialScore(cTestData.getVisuospatialScore());
        cTestResult.setExecuteScore(cTestData.getExecuteScore());
        cTestResultRepository.save(cTestResult);

        int logNum = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        for(int i = 0; i < cTestData.getAttentionLog().size(); i++){
            ExecuteLog executeLog = new ExecuteLog();
            executeLog.setLogNum(Long.valueOf(logNum));
            logNum++;
            executeLog.setLog(cTestData.getAttentionLog().get(i));
            executeLog.setLogTime(LocalDateTime.parse(cTestData.getAttentionLogTime().get(i), formatter));
            executeLog.setCTestResult(cTestResult);
            executeLogRepository.save(executeLog);
        }
        logNum = 0;
        for(int i = 0; i < cTestData.getVisuospatialLog().size(); i++){
            ExecuteLog executeLog = new ExecuteLog();
            executeLog.setLogNum(Long.valueOf(logNum));
            logNum++;
            executeLog.setLog(cTestData.getVisuospatialLog().get(i));
            executeLog.setLogTime(LocalDateTime.parse(cTestData.getVisuospatialLogTime().get(i), formatter));
            executeLog.setCTestResult(cTestResult);
            executeLogRepository.save(executeLog);
        }
        logNum = 0;
        for(int i = 0; i < cTestData.getExecuteLog().size(); i++){
            ExecuteLog executeLog = new ExecuteLog();
            executeLog.setLogNum(Long.valueOf(logNum));
            logNum++;
            executeLog.setLog(cTestData.getExecuteLog().get(i));
            executeLog.setLogTime(LocalDateTime.parse(cTestData.getExecuteLogTime().get(i), formatter));
            executeLog.setCTestResult(cTestResult);
            executeLogRepository.save(executeLog);
        }

        TestResult testResult = new TestResult();
        testResult.setCTestResult(cTestResult);
        testResult.setTestSubject(testSubjectRepository.findById(Long.valueOf(cTestData.getPatientID())).orElse(null));
        testResult.setTestStartTime(LocalDateTime.parse(cTestData.getStartTime(), formatter));
        testResult.setTestEndTime(LocalDateTime.parse(cTestData.getEndTime(), formatter));
        testResultRepository.save(testResult);

        return "good";
    }

    @GetMapping("/supervision-info")
    public ResponseEntity<Supervision> supervisionInfo(@RequestParam String supervisionLoginId){
        Supervision supervision = supervisionRepository.findByLoginId(supervisionLoginId);
        if (supervision == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(supervision);
    }

    @GetMapping("/patient-info")
    public List<TestSubject> patientInfo(@RequestParam Long supervisionId) {
        return testSubjectRepository.findAllBySupervisionId(supervisionId);
    }

    @GetMapping("/contents-list")
    public List<Contents> contentsList(){
        return contentsRepository.findAll();
    }

    @GetMapping("/device-list")
    public List<Device> deviceList(@RequestParam Long supervisionId) {
        Supervision supervision = supervisionRepository.findById(supervisionId).orElse(null);
        if (supervision == null) {
            return Collections.emptyList();
        }
        return deviceRepository.findAllByOrganizationId(supervision.getOrganization().getId());
    }

    @GetMapping("/all-test-result")
    public List<TestResultDTO> allTestResult(@RequestParam Long supervisionId){
        Supervision supervision = supervisionRepository.findById(supervisionId).orElse(null);
        if (supervision == null) {
            // Supervision이 존재하지 않으면 빈 리스트를 반환하거나 예외를 던질 수 있습니다.
            return new ArrayList<>();
        }

        List<TestSubject> testSubjectList = testSubjectRepository.findAllBySupervisionId(supervision.getId());
        List<TestResultDTO> allTestResultDTOs = new ArrayList<>();

        for (TestSubject testSubject : testSubjectList) {
            List<TestResult> testResults = testResultRepository.findAllByTestSubjectId(testSubject.getId());
            for (TestResult testResult : testResults) {
                TestResultDTO dto = convertToDto(testResult, testSubject.getName());
                allTestResultDTOs.add(dto);
            }
        }

        return allTestResultDTOs;
    }

    private TestResultDTO convertToDto(TestResult testResult, String testSubjectName) {
        return new TestResultDTO(
                testResult.getId(),
                testResult.getTestStartTime(),
                testResult.getTestEndTime(),
                testSubjectName
        );
    }

    /////////////////////////이 밑은 테스트용
    @GetMapping("/get-test")
    public TestResultData getTest() {
        TestResultData testResultData = new TestResultData();
        testResultData.setTestType("A");
        return testResultData;
    }
    @PostMapping("contents-list-push")
    public String contentsListPush(){
        Contents content1 = new Contents();
        content1.setContentsName("오락실 농구");
        content1.setContentsExplained("공을 잡아 던져 공을 골대에 넣는 훈련");
        content1.setCreatedDate(LocalDateTime.now());
        contentsRepository.save(content1);

        Contents content2 = new Contents();
        content2.setContentsName("공간지각 훈련");
        content2.setContentsExplained("큐브 바깥 상황을 보고 큐브 내에서 맞는 색상을 찾는 훈련");
        content2.setCreatedDate(LocalDateTime.now());
        contentsRepository.save(content2);

        Contents content3 = new Contents();
        content3.setContentsName("청기백기");
        content3.setContentsExplained("지시에 따라 청기 또는 백기를 올리거나 내리는 훈련");
        content3.setCreatedDate(LocalDateTime.now());
        contentsRepository.save(content3);

        Contents content4 = new Contents();
        content4.setContentsName("두더지 잡기");
        content4.setContentsExplained("무작위로 올라오는 두더지를 때려 들어가게하는 훈련");
        content4.setCreatedDate(LocalDateTime.now());
        contentsRepository.save(content4);

        Contents content5 = new Contents();
        content5.setContentsName("도형 골라내기");
        content5.setContentsExplained("도형과 맞는 모양의 구멍을 찾아 도형을 넣는 훈련");
        content5.setCreatedDate(LocalDateTime.now());
        contentsRepository.save(content5);

        Contents content6 = new Contents();
        content6.setContentsName("가위바위보 골키퍼");
        content6.setContentsExplained("골대로 날아오는 공에 붙어있는 가위바위보를 확인해 이길 수 있는 손 모양으로 공을 처내는 훈련");
        content6.setCreatedDate(LocalDateTime.now());
        contentsRepository.save(content6);

        Contents content7 = new Contents();
        content7.setContentsName("가위바위보 골키퍼");
        content7.setContentsExplained("날아오는 상자에 붙어있는 가위바위보를 지시에 맞게 비기거나 이기거나 지는 손 모양으로 쳐내는 훈련");
        content7.setCreatedDate(LocalDateTime.now());
        contentsRepository.save(content7);

        Contents content8 = new Contents();
        content8.setContentsName("그립미션");
        content8.setContentsExplained("날아오는 오브젝트를 카테고리에 맞게 잡아내는 훈련");
        content8.setCreatedDate(LocalDateTime.now());
        contentsRepository.save(content8);

        return "good";
    }
    @PostMapping("device-push")
    public String devicePush(){
        Device device = new Device();
        device.setDeviceNum(1);
        device.setDeviceName("Quest3");
        device.setOrganization(organizationRepository.findById(Long.valueOf(2)).get());
        deviceRepository.save(device);

        Device device2 = new Device();
        device2.setDeviceNum(2);
        device2.setDeviceName("Quest3");
        device2.setOrganization(organizationRepository.findById(Long.valueOf(2)).get());
        deviceRepository.save(device2);

        return "good";
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
