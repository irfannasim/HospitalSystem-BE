package com.sd.his.service;

import com.sd.his.enums.ResponseEnum;
import com.sd.his.model.ICDCode;
import com.sd.his.model.ICDCodeVersion;
import com.sd.his.model.ICDVersion;
import com.sd.his.repositories.ICDCodeRepository;
import com.sd.his.repositories.ICDCodeVersionRepository;
import com.sd.his.repositories.ICDVersionRepository;
import com.sd.his.request.ICDCodeCreateRequest;
import com.sd.his.utill.APIUtil;
import com.sd.his.utill.HISCoreUtil;
import com.sd.his.wrapper.ICDCodeVersionWrapper;
import com.sd.his.wrapper.ICDCodeWrapper;
import com.sd.his.wrapper.ICDVersionWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ICDService  {

    @Autowired
    private ICDCodeRepository codeRepository;
    @Autowired
    private ICDVersionRepository versionRepository;
    @Autowired
    private ICDCodeVersionRepository codeVersionRepository;


    public List<ICDVersionWrapper> versios() {
        return APIUtil.buildICDVersionWrapper(this.versionRepository.findAllByDeletedFalse());
    }

    public List<ICDCodeWrapper> codesNotDeleted() {
        return APIUtil.buildICDCodesWrapper(this.codeRepository.findAllByDeletedFalse());
    }

    @Transactional
    public ICDCode saveICD(ICDCodeCreateRequest createRequest) {
        ICDCode icd = new ICDCode(createRequest);
        return codeRepository.save(icd);
    }

    public boolean isICDCodeAlreadyExist(String iCDCode) {
        ICDCode icd = codeRepository.findByCode(iCDCode);
        if (HISCoreUtil.isValidObject(icd)) {
            return true;
        }
        return false;
    }

    public boolean isICDVersionNameAlreadyExist(String iCDVersionName) {
        ICDVersion icd = versionRepository.findByNameAndDeletedFalse(iCDVersionName);
        if (HISCoreUtil.isValidObject(icd)) {
            return true;
        }
        return false;
    }

    public boolean isICDCodeAlreadyExistAgainstICDCodeId(String iCDCode, long iCDCodeId) {
        ICDCode icd = codeRepository.findByCodeAndIdNot(iCDCode, iCDCodeId);
        if (HISCoreUtil.isValidObject(icd)) {
            return true;
        }
        return false;
    }

    public List<ICDCodeWrapper> findCodes(int offset, int limit) {
        Pageable pageable = new PageRequest(offset, limit);
        List<ICDCodeWrapper> icds = new ArrayList<>();
        List<ICDCode> paginatedICDs = codeRepository.findAllByDeletedFalse(pageable);
        if (!HISCoreUtil.isListEmpty(paginatedICDs)) {
            icds = APIUtil.buildICDCodeWrapper(paginatedICDs);
        }
        return icds;
    }

    public int countCodes() {
        return codeRepository.findAllByDeletedFalse().size();
    }

    public List<ICDVersionWrapper> findVersions(int offset, int limit) {
        Pageable pageable = new PageRequest(offset, limit);
        List<ICDVersionWrapper> icdsVersion = new ArrayList<>();
        List<ICDVersion> paginatedICDsVersion = versionRepository.findAllByDeletedFalse(pageable);
        if (!HISCoreUtil.isListEmpty(paginatedICDsVersion)) {
            icdsVersion = APIUtil.buildICDVersionWrapper(paginatedICDsVersion);
        }
        return icdsVersion;
    }

    public int countVersion() {
        return versionRepository.findAllByDeletedFalse().size();
    }

    public List<ICDCodeWrapper> searchCodes(String code, int offset, int limit) {
        List<ICDCodeWrapper> icds = new ArrayList<>();
        Pageable page = new PageRequest(offset, limit);
        List<ICDCode> searchedICDs = codeRepository.findAllByCodeContainingAndDeletedFalse(code, page);
        if (!HISCoreUtil.isListEmpty(searchedICDs)) {
            icds = APIUtil.buildICDCodeWrapper(searchedICDs);
        }
        return icds;
    }

    public int countSearchCodes(String code) {
        return codeRepository.findAllByCodeContainingAndDeletedFalse(code).size();
    }

    public List<ICDCodeVersionWrapper> searchCodeVersionByVersionName(int offset, int limit, String versionName,String code) {
        List<ICDCodeVersionWrapper> codeVersionsWrapper = new ArrayList<>();
        Pageable pageable = new PageRequest(offset, limit);

        List<ICDCodeVersion> searchedCVsByName = this.codeVersionRepository.findAllByVersion_NameContainingAndVersion_DeletedFalseOrIcd_CodeContainingAndIcd_DeletedFalse(versionName,code, pageable);
        if (!HISCoreUtil.isListEmpty(searchedCVsByName)) {
            codeVersionsWrapper = APIUtil.buildICDCodeVersionWrapper(searchedCVsByName);
        }
        return codeVersionsWrapper;
    }

    public int countSearchCodeVersionByVersionName(String versionName,String code) {
        return codeVersionRepository.findAllByVersion_NameContainingAndVersion_DeletedFalseOrIcd_CodeContainingAndIcd_DeletedFalse(versionName,code).size();
    }

    public List<ICDVersionWrapper> searchByVersion(String name, int offset, int limit) {
        List<ICDVersionWrapper> icdsVersion = new ArrayList<>();
        Pageable pageable = new PageRequest(offset, limit);
        List<ICDVersion> searchedICDsVersion = versionRepository.findAllByNameContainingAndDeletedFalse(name, pageable);
        if (!HISCoreUtil.isListEmpty(searchedICDsVersion)) {
            icdsVersion = APIUtil.buildICDVersionWrapper(searchedICDsVersion);
        }
        return icdsVersion;
    }

    public int countSearchByVersion(String name) {
        return versionRepository.findAllByNameContainingAndDeletedFalse(name).size();
    }

    @Transactional(rollbackOn = Throwable.class)
    public String deletedICD(Long icdId) {
        ICDCode icd = codeRepository.findOne(icdId);
        if (HISCoreUtil.isValidObject(icd)) {
            icd.setDeleted(true);
            codeRepository.save(icd);
            return ResponseEnum.SUCCESS.getValue();
        } else {
            return ResponseEnum.NOT_FOUND.getValue();
        }
    }

    @Transactional(rollbackOn = Throwable.class)
    public String deletedICDVersion(long icdId) {
        ICDVersion icdVersion = versionRepository.findOne(icdId);
        if (HISCoreUtil.isValidObject(icdVersion)) {
            icdVersion.setDeleted(true);
            versionRepository.save(icdVersion);
            return ResponseEnum.SUCCESS.getValue();
        } else {
            return ResponseEnum.NOT_FOUND.getValue();
        }
    }

    @Transactional(rollbackOn = Throwable.class)
    public String deletedAssociateICDCV(long icdId) {
        if (icdId > 0) {
            codeVersionRepository.delete(icdId);
            return ResponseEnum.SUCCESS.getValue();
        } else {
            return ResponseEnum.NOT_FOUND.getValue();
        }
    }

    @Transactional(rollbackOn = Throwable.class)
    public void updateICD(ICDCodeCreateRequest createRequest) {
        ICDCode icdCode = this.codeRepository.findOne(createRequest.getId());
        if (HISCoreUtil.isValidObject(icdCode)) {
            icdCode.setCode(createRequest.getCode());
            icdCode.setTitle(createRequest.getTitle());
            icdCode.setDescription(createRequest.getDescription());
            icdCode.setStatus(createRequest.isStatus());
            icdCode.setDeleted(false);
            icdCode.setUpdatedOn(System.currentTimeMillis());
        }
        this.codeRepository.save(icdCode);
    }

    @Transactional(rollbackOn = Throwable.class)
    public ICDVersion saveICDVersion(ICDVersionWrapper createRequest) {
        ICDVersion icdVersion = new ICDVersion(createRequest);
        return versionRepository.save(icdVersion);
    }

    public boolean isICDVersionNameAlreadyExistAgainstICDVersionNameId(String name, long id) {
        ICDVersion icdVersion = versionRepository.findByNameAndDeletedFalseAndIdNot(name, id);
        if (HISCoreUtil.isValidObject(icdVersion)) {
            return true;
        }
        return false;
    }

    @Transactional(rollbackOn = Throwable.class)
    public ICDVersion updateICDVersion(ICDVersionWrapper request) {
        ICDVersion icdVersion = versionRepository.findOne(request.getId());
        if (HISCoreUtil.isValidObject(icdVersion)) {
            icdVersion.setUpdatedOn(System.currentTimeMillis());
            icdVersion.setName(request.getName());
            icdVersion.setTitle(request.getTitle());
            icdVersion.setStatus(request.isStatus());
        }
        return icdVersion;
    }

    public List<ICDCodeVersionWrapper> codeVersions(int offset, int limit) {
        Pageable pageable = new PageRequest(offset, limit);
        List<ICDCodeVersionWrapper> cvs = new ArrayList<>();
        List<ICDCodeVersion> codeVersions = this.codeVersionRepository.findAllByOrderByVersion_name(pageable);
        if (codeVersions != null) {
            cvs = APIUtil.buildICDCodeVersionWrapper(codeVersions);
        }
        return cvs;
    }

    public int countCodeVersions() {
        return codeVersionRepository.findAllByOrderByVersion_name().size();
    }

    @Transactional(rollbackOn = Throwable.class)
    public List<ICDCodeVersion> saveAssociateICDCVs(ICDCodeVersionWrapper createRequest) {
        ICDCodeVersion associateICDCVs = null;
        List<ICDCodeVersion> codeVersions = new ArrayList<>();

        ICDVersion icdVersion = this.versionRepository.findOne(Long.parseLong(createRequest.getSelectedICDVersionId()));

        this.codeVersionRepository.deleteAllByVersion_id(icdVersion.getId());
        this.codeVersionRepository.flush();

        for (ICDCodeWrapper codeWrapper : createRequest.getiCDCodes()) {
            if (codeWrapper.isCheckedCode()) {
                associateICDCVs = new ICDCodeVersion();
                associateICDCVs.setVersion(icdVersion);
                associateICDCVs.setDescription(createRequest.getDescription());
                associateICDCVs.setIcd(this.codeRepository.findOne(codeWrapper.getId()));
                codeVersions.add(associateICDCVs);
            }
        }

        return codeVersionRepository.save(codeVersions);
    }

    @Transactional(rollbackOn = Throwable.class)
    public List<ICDCodeVersion> updateCVs(ICDCodeVersionWrapper createRequest) {
        ICDCodeVersion associateICDCVs = null;
        List<ICDCodeVersion> icdCodeVersions = new ArrayList<>();

        ICDVersion icdVersion = this.versionRepository.findOne(Long.parseLong(createRequest.getSelectedICDVersionId()));

        this.codeVersionRepository.deleteAllByVersion_id(icdVersion.getId());
        this.codeVersionRepository.flush();

        for (ICDCodeWrapper codeWrapper : createRequest.getSelectedICDCodes()) {
            associateICDCVs = new ICDCodeVersion();
            associateICDCVs.setVersion(icdVersion);
            associateICDCVs.setIcd(this.codeRepository.findOne(codeWrapper.getId()));

            icdCodeVersions.add(associateICDCVs);
        }

        return codeVersionRepository.save(icdCodeVersions);
    }

    public List<ICDCodeWrapper> getAssociatedICDCVByVId(long versionId) {
        if (versionId > 0) {
            List<ICDCodeVersion> dbList = codeVersionRepository.findAllByVersion_idAndVersion_deletedFalseAndIcd_DeletedFalse(versionId);
            if (dbList != null && dbList.size() > 0) {
                ///here we only want associated by version id
                return APIUtil.buildAssociatedICDCodesWrapper(dbList);
            }
        }
        return null;
    }
}
