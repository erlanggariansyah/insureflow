import React, { useEffect, useState } from "react";
import DataTable from "components/table/DataTable";
import MasterDataLayout from "components/layout/MasterDataLayout";
import { productService } from "services/productService";
import { MdFileUpload, MdPlayCircleOutline, MdQueryStats } from "react-icons/md";
import RateSimulatorModal from "components/modal/RateSimulatorModal";

export default function RateList() {
  const [data, setData] = useState([]);
  const [products, setProducts] = useState([]);
  const [genders, setGenders] = useState([]);
  const [loading, setLoading] = useState(true);
  const [isSimulatorOpen, setIsSimulatorOpen] = useState(false);

  useEffect(() => {
    Promise.all([
      productService.getAllRates(),
      productService.getAllProducts(),
      productService.getAllGenders()
    ])
      .then(([resRates, resProducts, resGenders]) => {
        setData(resRates.data || []);
        setProducts(resProducts.data || []);
        setGenders(resGenders.data || []);
      })
      .catch(err => console.error(err))
      .finally(() => setLoading(false));
  }, []);

  const columns = [
    { header: "ID", accessorKey: "id" },
    {
      header: "Product",
      accessorKey: "productId",
      cell: info => {
        const prodId = info.getValue();
        const prod = products.find(p => p.id === prodId);
        return <span className="font-bold text-navy-700 dark:text-white">{prod ? prod.name : prodId}</span>;
      }
    },
    {
      header: "Gender",
      accessorKey: "gender",
      cell: info => {
        const genderId = info.getValue();
        const gender = genders.find(g => g.id === genderId);
        return <span className={`rounded-full px-3 py-1 text-xs font-bold ${genderId === 1 ? 'bg-blue-100 text-blue-600' : 'bg-pink-100 text-pink-600'}`}>
          {gender ? gender.name : genderId}
        </span>;
      }
    },
    { header: "Age", accessorKey: "age" },
    {
      header: "Rate Value",
      accessorKey: "rate",
      cell: info => <span className="font-mono font-bold text-blue-600">{Number(info.getValue()).toFixed(6)}</span>
    },
  ];

  const workspaceActions = [
    {
      label: "Import Rate Sheet",
      desc: "Upload actuarial CSV",
      icon: <MdFileUpload className="h-6 w-6" />,
      color: "text-blue-600 bg-blue-50 dark:bg-blue-900/30",
      variant: "secondary",
      isPrimary: true,
      onClick: () => alert("Opening Uploader...")
    },
    {
      label: "Rate Simulator",
      desc: "Live premium testing",
      icon: <MdPlayCircleOutline className="h-6 w-6" />,
      color: "text-emerald-600 bg-emerald-50 dark:bg-emerald-900/30",
      isPrimary: true,
      onClick: () => setIsSimulatorOpen(true)
    },
    {
      label: "Statistical Analysis",
      desc: "Check variance & trend",
      icon: <MdQueryStats className="h-5 w-5" />,
      borderColor: "border-l-gray-400",
      onClick: () => alert("Opening Analytics...")
    }
  ];

  return (
    <MasterDataLayout title="Workspace" actions={workspaceActions}>
      {loading ? (
        <div className="flex h-64 items-center justify-center">
          <span className="text-gray-400 font-bold animate-pulse uppercase tracking-widest text-xs">Synchronizing Rates...</span>
        </div>
      ) : (
        <DataTable columns={columns} data={data} filename="Rates_Export" />
      )}

      <RateSimulatorModal 
        isOpen={isSimulatorOpen} 
        onClose={() => setIsSimulatorOpen(false)} 
        products={products} 
        genders={genders} 
      />
    </MasterDataLayout>
  );
}
